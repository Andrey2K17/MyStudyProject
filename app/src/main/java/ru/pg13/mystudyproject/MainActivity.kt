package ru.pg13.mystudyproject

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.lessons.lesson13.EnableView
import ru.pg13.mystudyproject.lessons.lesson13.ShowImage
import ru.pg13.mystudyproject.lessons.lesson13.ShowText
import ru.pg13.mystudyproject.lessons.lesson13.ShowView
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = (application as MyApplication).viewModel


        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }

        binding.changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }

        binding.progressBar.visibility = View.INVISIBLE

        binding.actionButton.setOnClickListener {
            viewModel.getJoke()
        }

        viewModel.observe(this) { state ->
            state.show(
                object : ShowView {
                    override fun show(show: Boolean) {
                        binding.progressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
                    }
                },
                object : EnableView {
                    override fun enable(enabled: Boolean) {
                        binding.actionButton.isEnabled = enabled
                    }
                },
                object : ShowText {
                    override fun show(text: String) {
                        binding.textView.text = text
                    }
                },
                object : ShowImage {
                    override fun show(id: Int) {
                        binding.changeButton.setImageResource(id)
                    }

                }
            )
        }

//        viewModel.observe(this) { state ->
//            state.show(binding.progressBar, binding.actionButton, binding.textView, binding.changeButton)
//        }
    }
}

