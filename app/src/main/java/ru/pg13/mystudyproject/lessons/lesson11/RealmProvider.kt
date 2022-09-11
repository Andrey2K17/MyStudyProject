package ru.pg13.mystudyproject.lessons.lesson11

import io.realm.Realm

interface RealmProvider {

    fun provide(): Realm
}

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}