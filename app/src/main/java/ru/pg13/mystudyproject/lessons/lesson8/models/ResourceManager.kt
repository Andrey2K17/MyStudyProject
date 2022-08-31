package ru.pg13.mystudyproject.lessons.lesson8.models

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringResId: Int) : String
}

class BaseResourceManager(private val context: Context): ResourceManager {
    override fun getString(stringResId: Int): String = context.getString(stringResId)
}