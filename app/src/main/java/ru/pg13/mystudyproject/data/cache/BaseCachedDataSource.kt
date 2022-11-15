package ru.pg13.mystudyproject.data.cache

import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pg13.mystudyproject.core.data.cache.CacheDataSource
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.core.data.cache.RealmToCommonDataMapper
import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.core.domain.NoCachedDataException
import ru.pg13.mystudyproject.data.CommonDataModel


abstract class BaseCachedDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>,
    private val commonDataMapper: RealmToCommonDataMapper<T>
) : CacheDataSource {

    protected abstract val dbClass: Class<T>

    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else
                return commonDataMapper.map(list.random())
        }
    }

    override suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm =
                    it.where(dbClass).equalTo("id", id).findFirst()
                return@withContext if (itemRealm == null) {
                    it.executeTransactionAsync { transaction ->
                        val newData = model.map(mapper)
                        transaction.insert(newData)
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        itemRealm.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}