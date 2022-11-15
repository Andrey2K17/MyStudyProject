package ru.pg13.mystudyproject.data.cache

import ru.pg13.mystudyproject.core.data.cache.RealmToCommonDataMapper
import ru.pg13.mystudyproject.data.CommonDataModel

class JokeRealmToCommonMapper : RealmToCommonDataMapper<JokeRealmModel> {
    override fun map(realmObject: JokeRealmModel) =
        CommonDataModel(realmObject.id, realmObject.text, true)
}