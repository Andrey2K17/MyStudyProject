package ru.pg13.mystudyproject

import androidx.lifecycle.ViewModel
import ru.pg13.mystudyproject.presentation.BaseCommunication
import ru.pg13.mystudyproject.presentation.BaseViewModel

interface Module<T : ViewModel> {
    fun getViewModel(): T

    abstract class Base<E, T : BaseViewModel<E>> : Module<T> {
        protected abstract fun getCommunications() : BaseCommunication<E>
    }
}