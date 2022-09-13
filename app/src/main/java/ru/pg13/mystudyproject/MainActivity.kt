package ru.pg13.mystudyproject

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
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
            state.show(binding.progressBar, binding.actionButton, binding.textView, binding.changeButton)
        }
    }
}

