package ru.pg13.mystudyproject

import android.app.Dialog
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.databinding.DialogBinding
import ru.pg13.mystudyproject.lessons.lesson3.SimpleTextWatcher
import ru.pg13.mystudyproject.lessons.lesson5.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModel

    private companion object {
        const val INITIAL = 0
        const val PROGRESS = 1
        const val SUCCESS = 2
        const val FAILED = 3
    }

    private var state = INITIAL

    private fun showDialog() {
        val dialog = Dialog(this)
        val view = DialogBinding.inflate(layoutInflater)
        dialog.setCancelable(false)
        view.closeButton.setOnClickListener {
            state = INITIAL
            dialog.dismiss()
        }
        dialog.setContentView(view.root)
        dialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreate ${savedInstanceState == null}")
        val view = binding.root
        setContentView(view)

        savedInstanceState?.let {
            state = it.getInt("screenState", 123)
        }

        val weatherStation = WeatherStation()
        weatherStation.addMan(Man("Фил", "Джексон"))
        weatherStation.addMan(Man("Иван", "Иванов"))
        weatherStation.addMan(Man("Григорий", "Голубев"))
        weatherStation.addMan(Man("Павел", "Ивлев"))

        weatherStation.updateWeather()

        Log.d(TAG, "State: $state")

        viewModel = (application as MyApplication).viewModel

        val observable = TextObservable()
        observable.observe(object : TextCallback {
            override fun updateText(str: String) = runOnUiThread {
                binding.timeTextView.text = str
            }
        })

        viewModel.init(observable)

        when (state) {
            FAILED -> showDialog()
            SUCCESS -> {
                Snackbar.make(binding.contentLayout, "Success", Snackbar.LENGTH_SHORT).show()
                state = INITIAL
            }
        }

        lesson4Checkbox()
        lesson3Button()
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
        binding.textInputEditText.addTextChangedListener(textWatcher)
        binding.textInputEditText.listenChanges {
            Log.d(TAG, "changed $it")
            binding.textInputLayout.isErrorEnabled = false }
    }

    private fun lesson4Checkbox() {
        //Первая лекция
        val spannableString = SpannableString(getString(R.string.agreement_full_text))
        binding.checkBox.text = spannableString

        binding.loginButton.isEnabled = false
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            binding.loginButton.isEnabled = isChecked
        }
    }

    private fun lesson3WithValidation() {
        //Здесь существует проблема с лишним вызовом метода проверки после подстановки преффикса
        binding.textInputEditText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                val input = p0.toString()
                if (input.endsWith("@g")) {
                    val fulMail = "${input}mail.com"
                    //при таком способе картека вернется в начало строки
                    //binding.textInputEditText.setText(fulMail)
                    binding.textInputEditText.setTextCorrectly(fulMail)
                }
                val valid = android.util.Patterns.EMAIL_ADDRESS.matcher(p0.toString()).matches()
                binding.textInputLayout.isErrorEnabled = !valid
                val error = if (valid) "" else getString(R.string.invalid_email_message)
                binding.textInputLayout.error = error
                if (valid) Toast.makeText(
                    this@MainActivity,
                    R.string.valida_email_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("screenState", state)
    }

    private fun lesson3Button() {
        binding.loginButton.setOnClickListener {
            if(EMAIL_ADDRESS.matcher(binding.textInputEditText.text.toString()).matches()) {
                hideKeyboard(binding.textInputEditText)
                binding.contentLayout.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                Snackbar.make(binding.loginButton, "Go to post login", Snackbar.LENGTH_SHORT).show()
                state = PROGRESS
                Handler(Looper.myLooper()!!).postDelayed({
                    state = FAILED
                    binding.contentLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    showDialog()
                }, 3000)
            } else {
                binding.textInputLayout.isErrorEnabled = true
                binding.textInputLayout.error = getString(R.string.invalid_email_message)
            }
        }
    }

    private val textWatcher: TextWatcher = object :SimpleTextWatcher() {
        override fun afterTextChanged(p0: Editable?) {
            Log.d(TAG, "changed ${p0.toString()}")
            binding.textInputLayout.isErrorEnabled = false
        }
    }

    private fun setText(text: String) {
        binding.textInputEditText.removeTextChangedListener(textWatcher)
        binding.textInputEditText.setTextCorrectly(text)
        binding.textInputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        binding.textInputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        binding.textInputEditText.addTextChangedListener(textWatcher)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        viewModel.clear()
        super.onDestroy()
    }
}

//изящное решение для textWatcher в случае если вы не ставите програмно текст.
fun TextInputEditText.listenChanges(block: (text: String) -> Unit) {
    addTextChangedListener(object : SimpleTextWatcher() {
        override fun afterTextChanged(p0: Editable?) {
            block.invoke(p0.toString())
        }
    })
}

fun TextInputEditText.setTextCorrectly(text: CharSequence) {
    setText(text)
    setSelection(text.length)
}

fun AppCompatActivity.hideKeyboard(view: View) {
    val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}