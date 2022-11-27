package ru.pg13.mystudyproject.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.presentation.CommonUiModel
import ru.pg13.mystudyproject.presentation.State

interface CommonViewModel {
    fun getItem()
    fun getItemList()
    fun changeItemStatus()
    fun chooseFavorites(favorites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel>>)
}