package ru.pg13.mystudyproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.domain.BaseViewModel
import ru.pg13.mystudyproject.presentation.FavoriteDataView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = (application as MyApplication).viewModel

        val jokeFavoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        jokeFavoriteDataView.listChanges { isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        jokeFavoriteDataView.handleChangeButton {
            viewModel.changeJokeStatus()
        }
        jokeFavoriteDataView.handleActionButton {
            viewModel.getJoke()
        }

        viewModel.observe(this) { state ->
            Log.d("test123", "state: $state")
            jokeFavoriteDataView.show(state)
        }

        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteFavoriteDataView)
        quoteFavoriteDataView.listChanges { isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        quoteFavoriteDataView.handleChangeButton {
            viewModel.changeJokeStatus()
        }
        quoteFavoriteDataView.handleActionButton {
            viewModel.getJoke()
        }

//        viewModel.observe(this) { state ->
//            Log.d("test123", "state: $state")
//            quoteFavoriteDataView.show(state)
//        }
    }
}

