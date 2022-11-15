package ru.pg13.mystudyproject.presentation

import android.content.Context
import ru.pg13.mystudyproject.core.ResourceManager

class BaseResourceManager(private val context: Context): ResourceManager {
    override fun getString(stringResId: Int): String = context.getString(stringResId)
}