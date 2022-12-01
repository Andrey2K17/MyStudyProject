package ru.pg13.mystudyproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.pg13.mystudyproject.MyApplication
import ru.pg13.mystudyproject.R

abstract class BaseFragment<T>: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.data_fragment, container, false)

    @StringRes
    protected abstract fun checkBoxText() : Int
    @StringRes
    protected abstract fun actionButtonText() : Int

    protected abstract fun getViewModel(app: MyApplication): BaseViewModel<T>
    protected abstract fun getCommunication(app: MyApplication): BaseCommunication<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as MyApplication
        val viewModel = getViewModel(application)

        val communication = getCommunication(application)
        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<T> {
            override fun change(id: T) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    viewModel.changeItemStatus(id)
                }.show()
            }
        }, communication)
        recyclerView.adapter = adapter

        viewModel.observeList(this) { adapter.update() }
        viewModel.getItemList()
    }

}