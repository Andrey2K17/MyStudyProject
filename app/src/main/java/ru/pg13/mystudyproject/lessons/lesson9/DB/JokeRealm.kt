package ru.pg13.mystudyproject.lessons.lesson9.DB

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm : RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var type: String = ""
}