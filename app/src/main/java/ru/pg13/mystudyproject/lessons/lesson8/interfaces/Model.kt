package ru.pg13.mystudyproject.lessons.lesson8.interfaces

interface Model {

    fun getJoke()

    fun init(callback: ResultCallback)

    fun clear()
}