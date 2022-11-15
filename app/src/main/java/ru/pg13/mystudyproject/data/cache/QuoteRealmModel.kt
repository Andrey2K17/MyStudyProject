package ru.pg13.mystudyproject.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealmModel : RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""

}