package com.davidmag.movienatic.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java9.util.concurrent.CompletableFuture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.HttpException

abstract class NetworkBoundResource<CacheObject, RequestObject> {

    private val TAG = NetworkBoundResource::class.java.name

    private val results = MediatorLiveData<Resource<CacheObject>>()

    init {
        results.value = Resource.loading(null)

        val cacheSource = loadFromCache()

        if(cacheSource.value == null){
            fetchFromNetwork(cacheSource)
        }
        else {
            results.addSource(cacheSource){cacheObject ->
                results.removeSource(cacheSource)

                if(shouldFetch(cacheObject)){
                    fetchFromNetwork(cacheSource)
                }
                else {
                    results.addSource(cacheSource){
                        setValue(Resource.success(it))
                    }
                }
            }
        }
    }

    private fun setValue(newValue : Resource<CacheObject>){
        if(results.value != newValue){
            results.value = newValue
        }
    }

    private fun fetchFromNetwork(cacheSource : LiveData<CacheObject>){
        Log.d(TAG, "Fetching from network...")

        results.addSource(cacheSource){
            setValue(Resource.loading(it))
        }

        doRequest(createCall()).thenAccept {response ->
            results.removeSource(cacheSource)

            response?.let {
                saveCallResult(response)
            }

            results.addSource(cacheSource){
                setValue(Resource.success(it))
            }
        }.exceptionally{e ->
            setValue(Resource.error(e, null))
            null
        }
    }

    @MainThread
    abstract fun shouldFetch(item : CacheObject) : Boolean

    @MainThread
    abstract fun loadFromCache() : LiveData<CacheObject>

    @MainThread
    abstract fun createCall() : Call<RequestObject>

    @WorkerThread
    abstract fun saveCallResult(item : RequestObject)

    private fun <T> doRequest(call : Call<T>) : CompletableFuture<T?> {
        val deferred = CompletableFuture<T?>()

        GlobalScope.launch {
            try{
                val response = call.execute()

                if(response.isSuccessful){
                    val body = response.body()
                    launch(Dispatchers.Main){
                        deferred.complete(body)
                    }
                }
                else{
                    throw HttpException(response)
                }
            }
            catch(e : Throwable){
                e.printStackTrace()
                launch(Dispatchers.Main){
                    deferred.completeExceptionally(e)
                }
            }
        }

        return deferred
    }


    fun asLiveData() : LiveData<Resource<CacheObject>>{
        return results
    }
}