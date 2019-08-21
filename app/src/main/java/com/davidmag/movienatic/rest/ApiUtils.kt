package com.davidmag.movienatic.rest

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

object ApiUtils {
    fun <T, Q> doRequest(call : Call<T>, postOnComplete : MutableLiveData<Q>? = null, extractResult: ((Response<T>) -> Q)) : Deferred<Q> {
        val deferred = CompletableDeferred<Q>()

        GlobalScope.launch {
            try{
                val response = call.execute()

                if(response.isSuccessful){
                    val extractedResult = extractResult(response)
                    deferred.complete(extractedResult)

                    postOnComplete?.let {
                        launch(Dispatchers.Main){
                            it.value = extractedResult
                        }
                    }
                }
                else{
                    throw HttpException(response)
                }
            }
            catch(e : Exception){
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    deferred.completeExceptionally(e)
                }
                postOnComplete?.let {
                    launch(Dispatchers.Main){
                        it.value = extractedResult
                    }
                }
            }
        }

        return deferred
    }
}