package com.davidmag.movienatic.data.source.remote.impl

import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.boundary.remote.ImageConfigsRemoteDatasource
import com.davidmag.movienatic.data.source.remote.api.ConfigurationsApi
import com.davidmag.movienatic.data.source.remote.mapper.ImageConfigsRemoteMapper
import com.davidmag.movienatic.domain.model.ImageConfigs
import io.reactivex.Maybe
import retrofit2.Call
import retrofit2.HttpException

class ImageConfigsRemoteDatasourceImpl(
    val configurationsApi: ConfigurationsApi
) : ImageConfigsRemoteDatasource {

    var currentCall : Call<*>? = null

    override fun fetch(): Maybe<ImageConfigs> {
        return Maybe.fromCallable {
            currentCall?.cancel()

            val call = configurationsApi.updateConfigurations(BuildConfig.API_KEY)

            currentCall = call

            val response = call.execute()

            if(response.isSuccessful){
               ImageConfigsRemoteMapper.toEntity(response.body()!!.images)
            }
            else {
               throw HttpException(response)
            }
        }
    }

    override fun cancel() {
        currentCall?.cancel()
        currentCall = null
    }
}