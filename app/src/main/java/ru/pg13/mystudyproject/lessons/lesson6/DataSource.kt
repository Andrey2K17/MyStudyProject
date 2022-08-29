package ru.pg13.mystudyproject.lessons.lesson6

interface DataSource {

    fun saveInt(key: String, value: Int)

    fun getInt(key: String): Int
}