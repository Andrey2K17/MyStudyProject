package ru.pg13.mystudyproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.lessons.lesson3.SimpleTextWatcher

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lesson3Button()
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
        binding.textInputEditText.addTextChangedListener(textWatcher)
        binding.textInputEditText.listenChanges { binding.textInputLayout.isErrorEnabled = false }
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

    private fun lesson3Button() {
        binding.loginButton.setOnClickListener {
            if(EMAIL_ADDRESS.matcher(binding.textInputEditText.text.toString()).matches()) {
                hideKeyboard(binding.textInputEditText)
                binding.loginButton.isEnabled = false
                Snackbar.make(binding.loginButton, "Go to post login", Snackbar.LENGTH_SHORT).show()
            } else {
                binding.textInputLayout.isErrorEnabled = true
                binding.textInputLayout.error = getString(R.string.invalid_email_message)
            }
        }
    }

    private val textWatcher: TextWatcher = object :SimpleTextWatcher() {
        override fun afterTextChanged(p0: Editable?) {
            val input = p0.toString()
            if (input.endsWith("@g")) {
                val fulMail = "${input}mail.com"
                //при таком способе картека вернется в начало строки
                //binding.textInputEditText.setText(fulMail)
                setText(fulMail)
            }
        }
    }

    private fun setText(text: String) {
        binding.textInputEditText.removeTextChangedListener(textWatcher)
        binding.textInputEditText.setTextCorrectly(text)
        binding.textInputEditText.addTextChangedListener(textWatcher)
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