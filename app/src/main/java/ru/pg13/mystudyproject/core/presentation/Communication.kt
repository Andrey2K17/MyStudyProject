package ru.pg13.mystudyproject.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import ru.pg13.mystudyproject.presentation.State

interface Communication {
    fun showState(state: State)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun isState(type: Int): Boolean
    fun getDiffResult(): DiffUtil.DiffResult
}