package com.davidmag.movienatic.data.source.local.dto

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class MovieDb : RealmObject() {
    @PrimaryKey
    var id : Int? = null

    var posterPath : String? = null
    var releaseDate : String? = null
    var genres : RealmList<GenreDb>? = null
    var backdropPath : String? = null
    var title: String? = null
    var overview: String? = null
}
    //var lastUpdate : Date? = null
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other !is MovieDb) return false
//
//        if (id != other.id) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return id ?: 0
//    }


