package com.davidmag.movienatic.data.source.local.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GenreDb : RealmObject() {
    @PrimaryKey
    var id : Int? = null
    var name : String? = null
}