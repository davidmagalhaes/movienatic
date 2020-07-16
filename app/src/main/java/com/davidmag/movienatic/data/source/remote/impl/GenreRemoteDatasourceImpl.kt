package com.davidmag.movienatic.data.source.remote.impl

import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.boundary.remote.GenreRemoteDatasource
import com.davidmag.movienatic.data.source.remote.api.GenreApi
import com.davidmag.movienatic.data.source.remote.mapper.GenreRemoteMapper
import com.davidmag.movienatic.domain.model.Genre
import io.reactivex.Maybe

class GenreRemoteDatasourceImpl(val genreApi: GenreApi) : GenreRemoteDatasource {
    override fun fetch(): Maybe<List<Genre>> {
        return genreApi.genres(
            BuildConfig.API_KEY,
            BuildConfig.DEFAULT_LANGUAGE
        )
        .map { GenreRemoteMapper.toEntity(it.genres) }

    }
}