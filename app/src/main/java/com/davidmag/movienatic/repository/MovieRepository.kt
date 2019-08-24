package com.davidmag.movienatic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.ImageConfigs
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.api.MovieApi
import com.davidmag.movienatic.rest.response.ConfigurationsApiResponse
import com.davidmag.movienatic.rest.response.LookupMoviesResponse
import com.davidmag.movienatic.util.Constants
import com.davidmag.movienatic.util.DateUtils
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import retrofit2.Call
import java.lang.UnsupportedOperationException
import java.util.*

object MovieRepository {

    private val TAG = MovieRepository.javaClass.name

    private val movieApi : MovieApi by lazy {
        App.retrofit.create(MovieApi::class.java)
    }

    fun getUpcomingMovies(page : Int? = null, language : String? = null, region : String? = null) : LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundRealmListResource<Movie, LookupMoviesResponse>(){
            override fun shouldFetch(item: List<Movie>): Boolean {
                return true
            }
            override fun createCall(): Call<LookupMoviesResponse> {
                return movieApi.getUpcomingMovies(BuildConfig.API_KEY, page, language, region)
            }
            override fun saveCallResult(item: LookupMoviesResponse, realmTransaction : Realm) {
                realmTransaction.copyToRealmOrUpdate(item.results)
            }
            override fun executeQuery(realm: Realm): RealmResults<Movie> {
                return realm.where(Movie::class.java).findAll()
            }
        }.asLiveData()
    }

    /*
    Função para procurar por um único filme pelo id. Por enquanto, carrega apenas do banco local.
    * */
    fun findMovie(id : Int): LiveData<Resource<Movie>>{
        return object : NetworkBoundRealmResource<Movie, Movie>(){
            override fun shouldFetch(item: Movie): Boolean {
                val now = Date()
                val holdCacheUntil = item.lastUpdate?.let {Date(it.time + Constants.MOVIE_REFRESH_TIME * 1000)}

                Log.d(TAG, "Verifying if image configurations cache should be updated...")
                Log.d(TAG, "lastRefresh: ${DateUtils.toIsoString(item.lastUpdate)}")
                Log.d(TAG, "holdCacheUntil: ${DateUtils.toIsoString(holdCacheUntil)}")

                if(holdCacheUntil == null || holdCacheUntil.before(now)){
                    Log.d(TAG, "PREPARING FOR MOVIE CACHE REFRESH!!!")
                }

                return holdCacheUntil == null || holdCacheUntil.before(now)
            }
            override fun createCall(): Call<Movie> {
                return movieApi.findMovie(id, BuildConfig.API_KEY)
            }
            override fun saveCallResult(item: Movie, realmTransaction : Realm) {
                item.lastUpdate = Date()
                item.genres?.let { realmTransaction.copyToRealmOrUpdate(it) }
                realmTransaction.copyToRealmOrUpdate(item)
            }
            override fun executeQuery(realm: Realm): Movie? {
                return realm.where(Movie::class.java).equalTo("id", id).findFirst()
            }
        }.asLiveData()
    }
}