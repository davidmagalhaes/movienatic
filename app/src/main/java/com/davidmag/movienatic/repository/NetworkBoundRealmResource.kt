package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmQuery
import kotlin.reflect.KClass

abstract class NetworkBoundRealmResource<CacheObject : RealmModel, RequestObject>
    : NetworkBoundResource<CacheObject, RequestObject>() {

    override fun loadFromCache(): LiveData<CacheObject> {
        val liveData = MutableLiveData<CacheObject>()

        Realm.getDefaultInstance().use { realm ->
            val result = executeQuery(realm)

            liveData.value = result

            if(result is RealmObject){
                result.addChangeListener<CacheObject> { res ->
                    liveData.value = res
                }
            }
        }

        return liveData
    }

    final override fun saveCallResult(item: RequestObject) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                saveCallResult(item, it)
            }
        }
    }

    abstract fun executeQuery(realm : Realm) : CacheObject?

    abstract fun saveCallResult(item: RequestObject, realmTransaction : Realm)
}

