package com.davidmag.movienatic.repository

import androidx.lifecycle.MutableLiveData

abstract class NetworkBoundResource<T> {

    private val data = MutableLiveData<>()
    private val exception = MutableLiveData<Throwable>()

    fun getLiveData() :

    abstract fun fetchData() : ApiResult<T>
    abstract fun shouldFetchData() : Boolean
    abstract fun loadFromCache() : T
}