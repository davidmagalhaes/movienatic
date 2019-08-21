package com.davidmag.movienatic.rest.configuration

import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.ImageConfigs
import com.davidmag.movienatic.rest.ApiUtils
import io.realm.Realm
import kotlinx.coroutines.Deferred

object ConfigurationsResourceClient {
    val imageConfigs = MutableLiveData<ImageConfigs>()

    private val configurationsResource by lazy {
        App.retrofit.create(ConfigurationsResource::class.java)
    }

    fun updateConfigurations() : Deferred<ConfigurationsResponse> {
        val call = configurationsResource.updateConfigurations(BuildConfig.API_KEY)

        return ApiUtils.doRequest(call){ response ->
            val body = response.body()!!
            val images = body.images

            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    it.copyToRealmOrUpdate(images)
                }
            }

            imageConfigs.postValue(images)
            body
        }
    }


    /*
    return ApiUtils.doRequest(call){ response, exception ->
            exception?.let {
                imageConfigs.postValue(null)
            }
            response?.let {
                val results = it.body()!!.images

                Realm.getDefaultInstance().use {realm ->
                    val imgConfig = realm.where(ImageConfigs::class.java).findFirst()

                    if(imgConfig == null){
                        realm.executeTransaction {
                            it.createObject(ImageConfigs::class.java, 1)
                        }
                    }

                    realm.executeTransaction {
                        it.copyToRealmOrUpdate(results)
                    }
                }

                imageConfigs.postValue(results)
                results
            }
        }
     */
}