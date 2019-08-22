package com.davidmag.movienatic.repository

import androidx.lifecycle.LiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.ImageConfigs
import com.davidmag.movienatic.rest.api.ConfigurationsApi
import com.davidmag.movienatic.rest.response.ConfigurationsApiResponse
import io.realm.Realm
import retrofit2.Call

object ConfigurationsRepository {
    val configurationsApi : ConfigurationsApi by lazy {
        App.retrofit.create(ConfigurationsApi::class.java)
    }

    fun getImageConfigurations() : LiveData<Resource<ImageConfigs>>{
        return object : NetworkBoundRealmResource<ImageConfigs, ConfigurationsApiResponse>(ImageConfigs::class.java){
            override fun shouldFetch(item: ImageConfigs): Boolean {
                return true
            }
            override fun createCall(): Call<ConfigurationsApiResponse> {
                return configurationsApi.updateConfigurations(BuildConfig.API_KEY)
            }
            override fun saveCallResult(item: ConfigurationsApiResponse, realmTransaction : Realm) {
                realmTransaction.copyToRealmOrUpdate(item.images)
            }
        }.asLiveData()
    }
}