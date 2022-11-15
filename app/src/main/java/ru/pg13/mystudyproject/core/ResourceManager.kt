package ru.pg13.mystudyproject.core

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringResId: Int) : String
}