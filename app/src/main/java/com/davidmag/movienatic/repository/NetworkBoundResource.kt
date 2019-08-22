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

    val TAG = NetworkBoundResource::class.java.name

    private val results = MediatorLiveData<Resource<CacheObject>>()

    init {
        results.value = Resource.loading(null)

        val cacheSource = loadFromCache()

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

    private fun setValue(newValue : Resource<CacheObject>){
        if(results.value != newValue){
            results.value = newValue
        }
    }

    private fun fetchFromNetwork(cacheSource : LiveData<CacheObject>){
        Log.d(TAG, "Fetching from network...")

        results.addSource(cacheSource){
            setValue(Resource.success(it))
        }

        val future = doRequest(createCall()){
            it
        }

        future.thenAccept {response ->
            results.removeSource(cacheSource)

            response?.let {
                saveCallResult(response)
            }

            results.addSource(cacheSource){
                setValue(Resource.success(it))
            }
        }.exceptionally{e ->
            results.addSource(cacheSource){
                setValue(Resource.error(e, it))
            }
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

    private fun <T, Q> doRequest(call : Call<T>, extractResult: ((T) -> Q)? = null) : CompletableFuture<Q?> {
        val deferred = CompletableFuture<Q?>()

        GlobalScope.launch {
            try{
                val response = call.execute()

                if(response.isSuccessful){
                    val body = response.body()
                    launch(Dispatchers.Main){
                        deferred.complete(if (body != null && extractResult != null) extractResult(body) else null)
                    }
                }
                else{
                    throw HttpException(response)
                }
            }
            catch(e : Exception){
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