package com.davidmag.movienatic.rest

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

object ApiUtils {
    fun <T, Q> doRequest(call : Call<T>, extractResult: ((Response<T>) -> Q)) : Deferred<Q> {
        val deferred = CompletableDeferred<Q>()

        GlobalScope.launch {
            try{
                val response = call.execute()

                if(response.isSuccessful){
                    deferred.complete(extractResult(response))
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
            }
        }

        return deferred
    }
}