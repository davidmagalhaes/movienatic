package com.davidmag.movienatic.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
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
                mediat.postValue(
                    GenericPresentationObject(
                        PresentationObject.VIEWTYPE_WAITING
                    )
                )
            }.flatMap {
                flowable.observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.firstOrNull()
                    }
                    .map {
                        if(it != null){
                            GenericPresentationObject<T>(
                                PresentationObject.VIEWTYPE_WAITING
                            )
                        }
                        else {
                            GenericPresentationObject(
                                PresentationObject.VIEWTYPE_EMPTY
                            )
                        }
                    }
                    .onErrorReturn {
                        GenericPresentationObject(
                            PresentationObject.VIEWTYPE_ERROR
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

    fun wrapSubmit(
        maybe: Maybe<Any>,
        mediator: MediatorLiveData<PresentationObject>? = null
    ) : LiveData<PresentationObject> {
        val mediat = mediator ?: MediatorLiveData()
        val source = wrapGeneric(
            maybe.map { listOf(it) }
        )

        mediat.addSource(source){
            mediat.postValue(it)
            mediat.removeSource(source)
        }

        return mediat
    }

    fun <T> attachOnce(
        maybe: Maybe<Any>,
        mediator: MediatorLiveData<T>
    ) : LiveData<PresentationObject>  {
        val source = LiveDataReactiveStreams.fromPublisher(
            maybe.observeOn(AndroidSchedulers.mainThread())
                .map {
                    GenericPresentationObject<Any>(
                        viewType = PresentationObject.VIEWTYPE_CONTENT
                    ) as PresentationObject
                }
                .onErrorReturnItem(
                    GenericPresentationObject<Any>(
                        viewType = PresentationObject.VIEWTYPE_ERROR
                    )
                )
                .toFlowable()
        )

        mediator.addSource(source){
            mediator.removeSource(source)
        }

        return source
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