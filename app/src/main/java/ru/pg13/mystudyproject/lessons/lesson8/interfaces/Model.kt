package ru.pg13.mystudyproject.lessons.lesson8.interfaces

interface Model {

    fun getJoke()

    fun init(callback: JokeCallback)

    fun clear()

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun chooseDataSource(favorites: Boolean)
}