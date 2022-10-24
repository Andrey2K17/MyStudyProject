package ru.pg13.mystudyproject.data.interfaces

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}