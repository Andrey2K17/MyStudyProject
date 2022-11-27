package ru.pg13.mystudyproject.presentation

import ru.pg13.mystudyproject.domain.CommonItem

fun List<CommonItem>.toUiList() = map{ it.to() }