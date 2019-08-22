package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmModel

abstract class NetworkBoundRealmListResource<CacheObject : RealmModel, RequestObject>(private val clazz : Class<CacheObject>)
    : NetworkBoundResource<List<CacheObject>, RequestObject>() {

    override fun loadFromCache(): LiveData<List<CacheObject>> {
        val liveData = MutableLiveData<List<CacheObject>>()

        Realm.getDefaultInstance().use { realm ->
            val resultList = realm.where(clazz).findAll()

            liveData.value = resultList

            resultList.addChangeListener { res ->
                liveData.value = res
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

    abstract fun saveCallResult(item: RequestObject, realmTransaction : Realm)
}