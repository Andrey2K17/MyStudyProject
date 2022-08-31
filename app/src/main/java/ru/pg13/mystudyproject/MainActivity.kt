package ru.pg13.mystudyproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.lessons.lesson8.TextCallback
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

        binding.progressBar.visibility = View.INVISIBLE

        binding.actionButton.setOnClickListener {
            binding.actionButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread {
                binding.actionButton.isEnabled = true
                binding.progressBar.visibility = View.INVISIBLE
                binding.textView.text = text
            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}

