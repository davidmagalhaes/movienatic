package com.davidmag.movienatic.util

import com.google.gson.*
import io.realm.Realm
import io.realm.RealmModel
import java.lang.IllegalArgumentException
import java.lang.reflect.Type

class RealmDeserializerHelper : JsonDeserializer<RealmModel> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): RealmModel {
        var realmObj : RealmModel? = null

        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                realmObj = it.createOrUpdateObjectFromJson((context?.deserialize(json, typeOfT) as RealmModel).javaClass, json!!.asString)
            }
        }

        if(realmObj == null){
            throw IllegalArgumentException()
        }

        return realmObj!!
    }

}