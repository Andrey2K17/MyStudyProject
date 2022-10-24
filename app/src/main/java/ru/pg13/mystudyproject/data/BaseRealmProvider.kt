package ru.pg13.mystudyproject.data

import io.realm.Realm
import ru.pg13.mystudyproject.data.interfaces.RealmProvider

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}