package ru.pg13.mystudyproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pg13.mystudyproject.presentation.JokesViewModel
import ru.pg13.mystudyproject.presentation.MainViewModel
import ru.pg13.mystudyproject.presentation.QuotesViewModel

class ViewModelsFactory(
    private val mainModule: MainModule,
    private val jokesModule: JokesModule,
    private val quotesModule: QuotesModule
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val module = when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> mainModule
            modelClass.isAssignableFrom(JokesViewModel::class.java) -> jokesModule
            modelClass.isAssignableFrom(QuotesViewModel::class.java) -> quotesModule
            else -> throw IllegalStateException("unknow type of viewModel")
        }
        return module.getViewModel() as T
    }
}