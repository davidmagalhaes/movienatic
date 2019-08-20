package com.davidmag.movienatic.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class ImageConfigs : RealmObject(), Serializable {
    @PrimaryKey
    var id : Int = 1
        private set

    var lastSync : Date = Date()
        private set

    @SerializedName("base_url")
    var baseUrl : String? = null
    @SerializedName("secure_base_url")
    var secureBaseUrl : String? = null
    @SerializedName("backdrop_sizes")
    var backdropSizes : RealmList<String>? = null
    @SerializedName("logo_sizes")
    var logoSizes : RealmList<String>? = null
    @SerializedName("poster_sizes")
    var posterSizes : RealmList<String>? = null
}