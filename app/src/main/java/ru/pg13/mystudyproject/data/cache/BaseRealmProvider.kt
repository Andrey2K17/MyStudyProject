package ru.pg13.mystudyproject.data.cache

import io.realm.Realm
import ru.pg13.mystudyproject.core.data.cache.RealmProvider

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}