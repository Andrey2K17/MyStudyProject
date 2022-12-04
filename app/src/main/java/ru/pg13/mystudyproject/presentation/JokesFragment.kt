package ru.pg13.mystudyproject.presentation

import ru.pg13.mystudyproject.R

class JokesFragment: BaseFragment<JokesViewModel, Int>() {
    override fun checkBoxText() = R.string.get_joke
    override fun actionButtonText() = R.string.show_favorite_joke
    override fun getViewModelClass() = JokesViewModel::class.java
}