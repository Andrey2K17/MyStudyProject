package ru.pg13.mystudyproject.domain

import java.io.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedJokesException : IOException()