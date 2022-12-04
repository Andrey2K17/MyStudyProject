package ru.pg13.mystudyproject

import ru.pg13.mystudyproject.core.data.cache.PersistentDataSource
import ru.pg13.mystudyproject.presentation.MainViewModel
import ru.pg13.mystudyproject.presentation.NavigationCommunication
import ru.pg13.mystudyproject.presentation.ScreenPosition

class MainModule(private val persistentDataSource: PersistentDataSource) : Module<MainViewModel> {
    override fun getViewModel() = MainViewModel(
        ScreenPosition.Base(persistentDataSource),
        NavigationCommunication.Base()
    )
}