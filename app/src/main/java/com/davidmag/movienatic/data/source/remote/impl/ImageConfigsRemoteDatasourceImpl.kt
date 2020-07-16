package com.davidmag.movienatic.data.source.remote.impl

import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.boundary.remote.ImageConfigsRemoteDatasource
import com.davidmag.movienatic.data.source.remote.api.ConfigurationsApi
import com.davidmag.movienatic.data.source.remote.mapper.ImageConfigsRemoteMapper
import com.davidmag.movienatic.domain.model.ImageConfigs
import io.reactivex.BackpressureStrategy
import io.reactivex.Maybe
import retrofit2.Call
import retrofit2.HttpException

class ImageConfigsRemoteDatasourceImpl(
    val configurationsApi: ConfigurationsApi
) : ImageConfigsRemoteDatasource {

    override fun fetch(): Maybe<ImageConfigs> {
        return configurationsApi.updateConfigurations(BuildConfig.API_KEY)
            .map {
                ImageConfigsRemoteMapper.toEntity(it.images)
            }
    }
}