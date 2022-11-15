package ru.pg13.mystudyproject.data.cache

import ru.pg13.mystudyproject.core.data.cache.RealmToCommonDataMapper
import ru.pg13.mystudyproject.data.CommonDataModel

class QuoteRealmToCommonMapper : RealmToCommonDataMapper<QuoteRealmModel> {

    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.id, realmObject.text, true)
}