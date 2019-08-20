package com.davidmag.movienatic.rest

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

object ApiUtils {
    fun <T, Q> doRequest(call : Call<T>, handleResults : (Response<T>?, Exception?) -> Q?) : CompletableDeferred<Q?> {
        val completableDeferred = CompletableDeferred<Q?>()

        GlobalScope.launch {
            try{
                val response = call.execute()

                if(response.isSuccessful){
                    completableDeferred.complete(handleResults(response, null))
                }
                else{
                    throw HttpException(response)
                }
            }
            catch(e : Exception){
                e.printStackTrace()
                completableDeferred.completeExceptionally(e)
                handleResults(null, e)
            }
        }

        return completableDeferred
    }
}