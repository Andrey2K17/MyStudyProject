package ru.pg13.mystudyproject.lessons.lesson13

import androidx.annotation.DrawableRes

interface ShowText {
    fun show(text: String)
}

interface ShowImage {
    fun show(@DrawableRes id: Int)
}

interface ShowView {
    fun show(text: Boolean)
}

interface EnableView {
    fun enable(enabled: Boolean)
}


