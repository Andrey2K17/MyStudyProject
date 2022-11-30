package ru.pg13.mystudyproject.core.domain

import ru.pg13.mystudyproject.domain.CommonItem

interface CommonInteractor<T> {
    suspend fun getItem(): CommonItem<T>
    suspend fun getItemList() : List<CommonItem<T>>
    suspend fun changeFavorites(): CommonItem<T>
    fun getFavorites(favorites: Boolean)
    suspend fun removeItem(id: T)
}