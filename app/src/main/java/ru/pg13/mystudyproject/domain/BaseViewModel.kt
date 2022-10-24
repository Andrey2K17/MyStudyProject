package ru.pg13.mystudyproject.domain

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pg13.mystudyproject.State
import ru.pg13.mystudyproject.domain.interfaces.Communication
import ru.pg13.mystudyproject.domain.interfaces.JokeInteractor

class BaseViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication,
    private val dispathcer: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun getJoke() = viewModelScope.launch(dispathcer) {
        communication.showState(State.Progress)
        interactor.getJoke().to().show(communication)
    }

    fun changeJokeStatus() = viewModelScope.launch(dispathcer) {
        if (communication.isState(State.INITIAL))
            interactor.changeFavorites().to().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) =
        interactor.getFavoriteJoke(favorites)


    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observer(owner, observer)
}