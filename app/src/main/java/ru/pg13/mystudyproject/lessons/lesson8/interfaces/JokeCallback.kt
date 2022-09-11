package ru.pg13.mystudyproject.lessons.lesson8.interfaces

import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

interface JokeCallback {

    fun provide(data: JokeUiModel)
}