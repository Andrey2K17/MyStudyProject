package ru.pg13.mystudyproject.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pg13.mystudyproject.core.presentation.Communication
import ru.pg13.mystudyproject.core.domain.CommonInteractor
import ru.pg13.mystudyproject.core.presentation.CommonViewModel

class BaseViewModel(
    private val interactor: CommonInteractor,
    private val communication: Communication,
    private val dispathcer: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel {

    override fun getItem() {
        viewModelScope.launch(dispathcer) {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    override fun changeItemStatus() {
        viewModelScope.launch(dispathcer) {
            if (communication.isState(State.INITIAL))
                interactor.changeFavorites().to().show(communication)
        }
    }

    override fun chooseFavorites(favorites: Boolean) = interactor.getFavoriteJoke(favorites)

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)
}