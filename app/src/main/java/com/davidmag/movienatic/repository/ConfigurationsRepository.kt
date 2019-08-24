package com.davidmag.movienatic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.ImageConfigs
import com.davidmag.movienatic.rest.api.ConfigurationsApi
import com.davidmag.movienatic.rest.response.ConfigurationsApiResponse
import com.davidmag.movienatic.util.Constants
import com.davidmag.movienatic.util.DateUtils
import io.realm.Realm
import retrofit2.Call
import java.util.*

object ConfigurationsRepository {

    private val TAG = ConfigurationsRepository.javaClass.name

    private val configurationsApi : ConfigurationsApi by lazy {
        App.retrofit.create(ConfigurationsApi::class.java)
    }

    fun getImageConfigurations(forceFetch : Boolean = false) : LiveData<Resource<ImageConfigs>>{
        return object : NetworkBoundRealmResource<ImageConfigs, ConfigurationsApiResponse>(){
            override fun shouldFetch(item: ImageConfigs): Boolean {
                val now = Date()
                val holdCacheUntil = Date(item.lastUpdate.time + Constants.IMAGECONFIGS_REFRESH_TIME * 1000)
                val willFetch = forceFetch || now.after(holdCacheUntil)

                Log.d(TAG, "Verifying if image configurations cache should be updated...")
                Log.d(TAG, "lastRefresh: ${DateUtils.toIsoString(item.lastUpdate)}")
                Log.d(TAG, "holdCacheUntil: ${DateUtils.toIsoString(holdCacheUntil)}")
                Log.d(TAG, "forceFetch: ${forceFetch}")

                if(willFetch){
                    Log.d(TAG, "PREPARING FOR IMAGECONFIGS CACHE REFRESH!!!")
                }

                return willFetch
            }
            override fun createCall(): Call<ConfigurationsApiResponse> {
                return configurationsApi.updateConfigurations(BuildConfig.API_KEY)
            }
            override fun saveCallResult(item: ConfigurationsApiResponse, realmTransaction : Realm) {
                item.images.lastUpdate = Date()
                realmTransaction.copyToRealmOrUpdate(item.images)
            }
            override fun executeQuery(realm: Realm): ImageConfigs? {
                return realm.where(ImageConfigs::class.java).findFirst()
            }
        }.asLiveData()
    }
}