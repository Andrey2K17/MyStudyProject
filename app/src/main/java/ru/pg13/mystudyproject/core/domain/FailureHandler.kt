package ru.pg13.mystudyproject.core.domain

import ru.pg13.mystudyproject.core.presentation.Failure


interface FailureHandler {
    fun handle(e: Exception): Failure
}