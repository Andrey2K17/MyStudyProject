package ru.pg13.mystudyproject.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pg13.mystudyproject.core.domain.CommonInteractor
import ru.pg13.mystudyproject.core.presentation.CommonCommunication
import ru.pg13.mystudyproject.core.presentation.CommonViewModel
import ru.pg13.mystudyproject.domain.CommonItem

abstract class BaseViewModel<T>(
    private val name: String,
    private val interactor: CommonInteractor<T>,
    val communication: CommonCommunication<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel<T> {

    init {
        Log.d("BaseViewModelTag", "init $name")
    }

    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    override fun getItemList() {
        viewModelScope.launch(dispatcher) {
            communication.showDataList(interactor.getItemList().toUiList())
        }
    }

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL)) {
                interactor.changeFavorites().to().show(communication)
                communication.showDataList(interactor.getItemList().toUiList())
            }
        }
    }

    override fun changeItemStatus(id: T) {
        viewModelScope.launch(dispatcher) {
            interactor.removeItem(id)
            communication.showDataList(interactor.getItemList().toUiList())
        }
    }

    override fun chooseFavorites(favorites: Boolean) = interactor.getFavorites(favorites)
    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)

    override fun observeList(
        owner: LifecycleOwner,
        observer: Observer<List<CommonUiModel<T>>>
    ) = communication.observeList(owner, observer)

    override fun onCleared() {
        super.onCleared()
        Log.d("BaseViewModelTag", "onCleared $name")
    }
}

fun <T> List<CommonItem<T>>.toUiList() = map { it.to() }


class JokesViewModel(
    interactor: CommonInteractor<Int>,
    communication: CommonCommunication<Int>
) : BaseViewModel<Int>("jokes", interactor, communication)

class QuotesViewModel(
    interactor: CommonInteractor<String>,
    communication: CommonCommunication<String>
) : BaseViewModel<String>("quotes", interactor, communication)