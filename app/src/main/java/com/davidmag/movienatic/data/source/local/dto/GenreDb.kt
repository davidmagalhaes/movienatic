package com.davidmag.movienatic.data.source.local.dto

import io.realm.RealmObject
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.RealmResults
import io.realm.RealmObject.asChangesetObservable
import io.realm.RealmObject.asFlowable

open class GenreDb : RealmObject() {
    @PrimaryKey
    var id : Int? = null
    var name : String? = null
}