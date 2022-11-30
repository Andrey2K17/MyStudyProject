package ru.pg13.mystudyproject.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.pg13.mystudyproject.MyApplication
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CommonDataRecyclerAdapter<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = (application as MyApplication).viewModel
        val jokeCommunication = (application as MyApplication).jokeCommunication
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<Int> {
            override fun change(id: Int) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    viewModel.changeItemStatus(id)
                }.show()
            }
        }, jokeCommunication)
        recyclerView.adapter = adapter

        viewModel.observeList(this) { adapter.update() }
        viewModel.getItemList()
    }
}

