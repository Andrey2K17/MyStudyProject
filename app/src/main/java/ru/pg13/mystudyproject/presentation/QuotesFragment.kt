package ru.pg13.mystudyproject.presentation

import ru.pg13.mystudyproject.MyApplication
import ru.pg13.mystudyproject.R

class QuotesFragment : BaseFragment<String>() {
    override fun getViewModel(app: MyApplication) = app.quoteViewModel
    override fun getCommunication(app: MyApplication) = app.quoteCommunication
    override fun checkBoxText() = R.string.show_favorite_quote
    override fun actionButtonText() = R.string.get_quote
}