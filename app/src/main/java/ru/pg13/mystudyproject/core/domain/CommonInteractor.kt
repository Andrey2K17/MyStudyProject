package ru.pg13.mystudyproject.core.domain

import ru.pg13.mystudyproject.domain.CommonItem

interface CommonInteractor {

    suspend fun getItem() : CommonItem

    suspend fun changeFavorites() : CommonItem

    fun getFavoriteJoke(favorites: Boolean)
}