package ru.pg13.mystudyproject.core.data.net

interface CommonDataModelMapper<T> {
    fun map(id: Int, text: String, cached: Boolean): T
}