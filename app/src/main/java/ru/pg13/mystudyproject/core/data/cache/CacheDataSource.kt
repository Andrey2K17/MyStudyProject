package ru.pg13.mystudyproject.core.data.cache

import ru.pg13.mystudyproject.core.data.net.ChangeStatus
import ru.pg13.mystudyproject.core.data.net.DataFetcher


interface CacheDataSource : DataFetcher, ChangeStatus