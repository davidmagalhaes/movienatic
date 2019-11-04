package com.davidmag.movienatic.data.source.local.impl

import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.data.source.local.mapper.MovieLocalMapper
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.realm.Realm

class MovieLocalDataSourceImpl : MovieLocalDataSource {

    val realm = Realm.getDefaultInstance()

    override fun cache(movies: List<Movie>): Maybe<*> {
        return Maybe.fromCallable {
            Realm.getDefaultInstance().use { r ->
                r.executeTransaction { transaction ->
                    transaction.copyToRealmOrUpdate(MovieLocalMapper.toDto(movies))
                }
            }
        }
    }

    override fun patch(movie: Movie): Maybe<*> {
        return Maybe.fromCallable {
            realm.executeTransaction { transaction ->
                transaction.copyToRealmOrUpdate(MovieLocalMapper.toDto(movie))
            }
        }
    }

    override fun get(id : String?, genreId : Int?) : Flowable<List<Movie>> {
        val query = realm.where(MovieDb::class.java)

        if(id != null){
            query.equalTo("id", id)
        }

        if(genreId != null){
            //query.equalTo("genres.id", genreId)
        }

        return query.findAllAsync().asFlowable().map {
            MovieLocalMapper.toEntity(it)
        }
    }
}