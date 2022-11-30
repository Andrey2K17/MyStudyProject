package ru.pg13.mystudyproject.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.pg13.mystudyproject.presentation.CommonUiModel

interface ListCommunication<T> {
    fun getList(): List<CommonUiModel<T>>
    fun showDataList(list: List<CommonUiModel<T>>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)
}