package com.davidmag.movienatic.data.source.local.impl

import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.data.source.local.mapper.GenreLocalMapper
import com.davidmag.movienatic.data.source.local.mapper.MovieLocalMapper
import com.davidmag.movienatic.domain.model.Genre
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.realm.Realm

class MovieLocalDataSourceImpl : MovieLocalDataSource {

    override fun cache(movies: List<Movie>): Maybe<*> {
        return Maybe.fromCallable {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { transaction ->
                    transaction.copyToRealmOrUpdate(MovieLocalMapper.toDto(movies))
                }
            }
        }
    }

    override fun patch(movie: Movie): Maybe<*> {
        return Maybe.fromCallable {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { transaction ->
                    transaction.copyToRealmOrUpdate(MovieLocalMapper.toDto(movie))
                }
            }
        }
    }

    override fun get(id : String?, genreId : Int?) : Flowable<List<Movie>> {
        return Realm.getDefaultInstance().use { realm ->
            val query = realm.where(MovieDb::class.java)

            if(id != null){
                query.equalTo("id", id)
            }

            if(genreId != null){
                query.equalTo("genres.id", genreId)
            }

            query.findAll().asFlowable().map {
                MovieLocalMapper.toEntity(it)
            }
        }
    }
}