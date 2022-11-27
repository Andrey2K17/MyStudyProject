package ru.pg13.mystudyproject.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.presentation.CommonUiModel

interface ListCommunication {
    fun showDataList(list: List<CommonUiModel>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel>>)
}