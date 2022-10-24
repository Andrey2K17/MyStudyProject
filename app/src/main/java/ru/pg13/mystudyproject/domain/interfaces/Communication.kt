package ru.pg13.mystudyproject.domain.interfaces

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.State

interface Communication {
    fun showState(state: State)
    fun observer(owner: LifecycleOwner, observer: Observer<State>)
    fun isState(type: Int): Boolean
}