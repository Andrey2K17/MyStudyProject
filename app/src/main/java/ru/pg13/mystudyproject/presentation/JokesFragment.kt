package ru.pg13.mystudyproject.presentation

import ru.pg13.mystudyproject.MyApplication
import ru.pg13.mystudyproject.R

class JokesFragment: BaseFragment<Int>() {
    override fun checkBoxText() = R.string.get_joke
    override fun actionButtonText() = R.string.show_favorite_joke
    override fun getViewModel(app: MyApplication) = app.jokeViewModel
    override fun getCommunication(app: MyApplication) = app.jokeCommunication
}