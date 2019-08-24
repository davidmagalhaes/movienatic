package com.davidmag.movienatic.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Genre : RealmObject() {
    @PrimaryKey
    @SerializedName("id")
    var id : Int? = null
    @SerializedName("name")
    var name : String? = null
}