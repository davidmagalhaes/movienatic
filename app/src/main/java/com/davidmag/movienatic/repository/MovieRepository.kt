package com.davidmag.movienatic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.App
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.model.Movie
import com.davidmag.movienatic.rest.api.MovieApi
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

    private var lastRefresh : Date? = null

    private val movieApi : MovieApi by lazy {
        App.retrofit.create(MovieApi::class.java)
    }

    fun getUpcomingMovies(forceFetch : Boolean = false, page : Int? = null, language : String? = null, region : String? = null) : LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundRealmListResource<Movie, LookupMoviesResponse>(){
            override fun shouldFetch(item: List<Movie>): Boolean {
                val now = Date()
                val holdCacheUntil = lastRefresh?.let {
                    Date(it.time + Constants.MOVIES_REFRESH_TIME * 1000)
                }
                val willFetch = forceFetch || holdCacheUntil == null || now.after(holdCacheUntil)

                Log.d(TAG, "Verifying if movie cache should be updated...")
                Log.d(TAG, "lastRefresh: ${DateUtils.toIsoString(lastRefresh)}")
                Log.d(TAG, "holdCacheUntil: ${DateUtils.toIsoString(holdCacheUntil)}")
                Log.d(TAG, "forceFetch: ${forceFetch}")

                if(willFetch){
                    Log.d(TAG, "PREPARING FOR MOVIE CACHE REFRESH!!!")
                }

                return willFetch
            }
            override fun createCall(): Call<LookupMoviesResponse> {
                return movieApi.getUpcomingMovies(BuildConfig.API_KEY, page, language, region)
            }
            override fun saveCallResult(item: LookupMoviesResponse, realmTransaction : Realm) {
                lastRefresh = Date()
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
        val liveData = MutableLiveData<Resource<Movie>>()

        liveData.value = Resource.loading(null)

        Realm.getDefaultInstance().use {realm ->
            realm.where(Movie::class.java).equalTo("id", id).findFirst()?.let {
                liveData.value = Resource.success(it)
            }
        }

        return liveData
    }
}