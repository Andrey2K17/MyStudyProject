package ru.pg13.mystudyproject.domain

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.State
import ru.pg13.mystudyproject.domain.interfaces.Communication

class BaseCommunication: Communication {
    private val liveData = MutableLiveData<State>()

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun observer(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }
}