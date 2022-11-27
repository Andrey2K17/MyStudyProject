 package ru.pg13.mystudyproject.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.core.presentation.CommonCommunication

 class BaseCommunication: CommonCommunication {
    private val liveData = MutableLiveData<State>()
    private val listLiveData = MutableLiveData<List<CommonUiModel>>()

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun showDataList(list: List<CommonUiModel>) {
        listLiveData.value = list
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel>>) {
        listLiveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }
}