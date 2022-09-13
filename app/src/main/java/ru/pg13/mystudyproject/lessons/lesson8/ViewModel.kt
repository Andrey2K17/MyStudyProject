package ru.pg13.mystudyproject.lessons.lesson8

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pg13.mystudyproject.lessons.lesson13.*
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.Model

class ViewModel(
    private val model: Model,
    private val communication: Communication
) : ViewModel() {

    fun getJoke() = viewModelScope.launch {
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun changeJokeStatus() = viewModelScope.launch {
        model.changeJokeStatus()?.show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observer(owner, observer)

    sealed class State {
        fun show(progress: ShowView, button: EnableView, textView: ShowText, imageButton: ShowImage) {
            show(progress, button)
            show(textView, imageButton)
        }

        protected open fun show(progress: ShowView, button: EnableView) {}
        protected open fun show(textView: ShowText, imageButton: ShowImage) {}

        object Progress : State() {
            override fun show(progress: ShowView, button: EnableView) {
                progress.show(true)
                button.enable(false)
            }
        }

        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(progress: ShowView, button: EnableView) {
                progress.show(true)
                button.enable(false)
            }
            override fun show(textView: ShowText, imageButton: ShowImage) {
                textView.show(text)
                imageButton.show(id)
            }
        }
    }
}