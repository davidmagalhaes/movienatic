package com.davidmag.movienatic.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers

object ResultWrapper {
    fun <T> wrap(flowable : Flowable<T>,
                 mediator : MediatorLiveData<Result<T>>? = null
    ) : LiveData<Result<T>> {
        val source = LiveDataReactiveStreams.fromPublisher(flowable
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if(it != null) Result.withData(it) else Result.empty()
            }
            .onErrorReturn {
                it.printStackTrace()
                Result.error(it)
            }
        )

        if(mediator != null) {
            mediator.addSource(source) {
                mediator.postValue(it)
            }

            mediator.postValue(Result.waiting())
        }

        return mediator ?: source
    }

    fun <T> wrapFirst(flowable: Flowable<List<T>>,
                      mediator : MediatorLiveData<Result<T>>? = null
    ) : LiveData<Result<T>> {
        val source = LiveDataReactiveStreams.fromPublisher(flowable
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if(it.isNotEmpty()) Result.withData(it.first()) else Result.empty()
            }
            .onErrorReturn {
                it.printStackTrace()
                Result.error(it)
            }
        )

        if(mediator != null){
            mediator.addSource(source){
                mediator.postValue(it)
            }

            mediator.postValue(Result.waiting())
        }

        return mediator ?: source
    }

    fun <T> wrap(maybe : Maybe<T>) : LiveData<Result<T>> {
        val source = LiveDataReactiveStreams.fromPublisher(maybe
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if(it != null) Result.withData(it) else Result.empty()
            }
            .onErrorReturn {
                it.printStackTrace()
                Result.error(it)
            }
            .toFlowable()
        )

        val mediator = MediatorLiveData<Result<T>>()

        mediator.addSource(source){
            mediator.removeSource(source)
            mediator.postValue(it)
        }

        mediator.postValue(Result.waiting())

        return mediator
    }
}