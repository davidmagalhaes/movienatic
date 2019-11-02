package com.davidmag.movienatic.data.source.local.dto

import io.realm.RealmList
import io.realm.RealmObject
import java.util.*

open class ImageConfigsDb : RealmObject() {
    var id : Int = 1

    var lastUpdate : String? = null

    //@SerializedName("base_url")
    var baseUrl : String? = null
    //@SerializedName("secure_base_url")
    var secureBaseUrl : String? = null
    //@SerializedName("backdrop_sizes")
    var backdropSizes : RealmList<String>? = null
    //@SerializedName("logo_sizes")
    var logoSizes : RealmList<String>? = null
    //@SerializedName("poster_sizes")
    var posterSizes : RealmList<String>? = null
}