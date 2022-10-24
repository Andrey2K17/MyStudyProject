package ru.pg13.mystudyproject.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.pg13.mystudyproject.core.Mapper

open class JokeRealmModel : RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""

    override fun to() = JokeDataModel(id, text, true)
}