package ru.pg13.mystudyproject.presentation

import ru.pg13.mystudyproject.MyApplication
import ru.pg13.mystudyproject.R

class QuotesFragment : BaseFragment<QuotesViewModel, String>() {
    override fun checkBoxText() = R.string.show_favorite_quote
    override fun actionButtonText() = R.string.get_quote
    override fun getViewModelClass() = QuotesViewModel::class.java
}