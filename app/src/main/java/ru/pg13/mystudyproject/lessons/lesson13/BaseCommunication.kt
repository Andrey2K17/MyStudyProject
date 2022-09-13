package ru.pg13.mystudyproject.lessons.lesson13

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel

class BaseCommunication: Communication {
    private val liveData = MutableLiveData<ViewModel.State>()

    override fun showState(state: ViewModel.State) {
        liveData.value = state
    }

    override fun observer(owner: LifecycleOwner, observer: Observer<ViewModel.State>) {
        liveData.observe(owner, observer)
    }
}