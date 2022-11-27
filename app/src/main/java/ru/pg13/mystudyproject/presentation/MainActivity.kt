package ru.pg13.mystudyproject.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ru.pg13.mystudyproject.MyApplication
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.data.CommonDataModel
import ru.pg13.mystudyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = (application as MyApplication).viewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.hasFixedSize()
        val adapter = CommonDataRecyclerAdapter()
        recyclerView.adapter = adapter
        viewModel.observeList(this) { list ->
            adapter.show(list)
        }
        viewModel.getItemList()

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        favoriteDataView.linkWith(viewModel)

        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

//        val quoteViewModel = (application as MyApplication).quoteViewModel
//
//        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteFavoriteDataView)
//        quoteFavoriteDataView.linkWith(quoteViewModel)
//        quoteViewModel.observe(this) { state ->
//            quoteFavoriteDataView.show(state)
//        }
    }
}

