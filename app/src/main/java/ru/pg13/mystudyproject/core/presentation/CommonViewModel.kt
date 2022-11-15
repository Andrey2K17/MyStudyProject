package ru.pg13.mystudyproject.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.presentation.State

interface CommonViewModel {
    fun getItem()
    fun changeItemStatus()
    fun chooseFavorites(favorites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
}