package ru.pg13.mystudyproject.lessons.lesson13

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel

interface Communication {

    fun showState(state: ViewModel.State)

    fun observer(owner: LifecycleOwner, observer: Observer<ViewModel.State>)
}