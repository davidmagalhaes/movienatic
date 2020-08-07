package com.davidmag.movienatic.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers

object PresentationWrapper {

    fun <T> wrapGeneric(
        flowable: Flowable<List<T>>,
        mediator: MediatorLiveData<GenericPresentationObject<T>>? = null,
        removeAfterFirst: Boolean = false
    ): LiveData<GenericPresentationObject<T>> {
        val mediat = mediator ?: MediatorLiveData()
        val source = LiveDataReactiveStreams.fromPublisher(
            Flowable.fromCallable {
                mediat.postValue(GenericPresentationObject(
                    viewType = PresentationObject.DEFAULT_VIEWTYPE_WAITING
                ))
            }.flatMap {
                flowable.observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.firstOrNull()
                    }
                    .map {
                        if(it != null){
                            GenericPresentationObject(
                                viewType = PresentationObject.DEFAULT_VIEWTYPE_CONTENT,
                                value = it
                            )
                        }
                        else {
                            GenericPresentationObject(
                                viewType = PresentationObject.DEFAULT_VIEWTYPE_EMPTY
                            )
                        }
                    }
                    .onErrorReturn {
                        GenericPresentationObject(
                            viewType = PresentationObject.DEFAULT_VIEWTYPE_ERROR,
                            exception = it
                        )
                    }
            }
        )

        mediat.addSource(source){
            mediat.postValue(it)
            if(removeAfterFirst){
                mediat.removeSource(source)
            }
        }

        return mediat
    }

    fun <T> wrapGeneric(
        maybe: Maybe<List<T>>,
        mediator: MediatorLiveData<GenericPresentationObject<T>>? = null
    ): LiveData<GenericPresentationObject<T>> {
        return wrapGeneric(
            maybe.toFlowable(),
            mediator,
            true
        )
    }

    @Suppress("UNCHECKED_CAST")
    fun wrapSubmit(
        maybe: Maybe<Any>,
        mediator: MediatorLiveData<GenericPresentationObject<Any>>? = null
    ) : LiveData<PresentationObject> {
        return wrapGeneric(
            maybe.map {
                listOf(it)
            },
            mediator
        ) as LiveData<PresentationObject>
    }

    fun <T> attachOnce(
        maybe: Maybe<Any>,
        mediator: MediatorLiveData<T>
    )  {
        val source = LiveDataReactiveStreams.fromPublisher(
            maybe.observeOn(AndroidSchedulers.mainThread())
                .toFlowable()
        )

        mediator.addSource(source){
            mediator.removeSource(source)
        }
    }

    fun <T : PresentationObject> wrap(
        flowable : Flowable<List<T>>,
        mediator : MediatorLiveData<List<T>>? = null,
        removeAfterFirst: Boolean = false
    ): LiveData<List<T>> {
        val source = LiveDataReactiveStreams.fromPublisher(
            flowable.observeOn(AndroidSchedulers.mainThread())
        )

        mediator?.addSource(source){
            mediator.postValue(it)
            if(removeAfterFirst){
                mediator.removeSource(source)
            }
        }

        return mediator ?: source
    }

    fun <T : PresentationObject> wrap(
        maybe : Maybe<List<T>>,
        mediator : MediatorLiveData<List<T>>? = null
    ): LiveData<List<T>> {
        return wrap(
            maybe.toFlowable(),
            mediator,
            true
        )
    }

    fun <T : PresentationObject> wrapFirst(
        flowable : Flowable<List<T>>,
        mediator : MediatorLiveData<T>? = null,
        removeAfterFirst: Boolean = false
    ): LiveData<T?> {
        val source = LiveDataReactiveStreams.fromPublisher(
            flowable.observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.firstOrNull()
                }
        )

        mediator?.addSource(source){
            mediator.postValue(it)
            if(removeAfterFirst){
                mediator.removeSource(source)
            }
        }

        return mediator ?: source
    }
}