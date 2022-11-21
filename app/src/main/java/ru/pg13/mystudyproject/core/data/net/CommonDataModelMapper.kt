package ru.pg13.mystudyproject.core.data.net

interface CommonDataModelMapper<T, E> {
    fun map(id: E, text: String, cached: Boolean): T
}