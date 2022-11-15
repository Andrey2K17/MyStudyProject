package ru.pg13.mystudyproject.core.domain

import java.io.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedDataException : IOException()