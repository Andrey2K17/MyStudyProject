package ru.pg13.mystudyproject.core.data.cache

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}