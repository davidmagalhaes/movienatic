package com.davidmag.movienatic.presentation.common

interface Result<T> {

    val data : T?
    val error : Throwable?

    fun isSuccessful()      = this is DataResult || this is EmptyResult
    fun hasReturnedData()   = this is DataResult
    fun isEmpty()           = this is EmptyResult
    fun hasFailed()         = this is ErrorResult
    fun isWaiting()         = this is WaitingResult

    class DataResult<T>(
        override val data: T,
        override val error : Throwable? = null
    ) : Result<T>

    class ErrorResult<T>(
        override val error : Throwable,
        override val data: T? = null
    ) : Result<T>

    class EmptyResult<T>(
        override val data: T? = null,
        override val error : Throwable? = null
    ) : Result<T>

    class WaitingResult<T>(
        override val data: T? = null,
        override val error : Throwable? = null
    ) : Result<T>

    companion object {
        fun <T> withData(data : T) : Result<T> {
            return DataResult(data)
        }

        fun <T> error(error : Throwable) : Result<T> {
            return ErrorResult(error)
        }

        fun <T> empty() : Result<T> {
            return EmptyResult()
        }

        fun <T> waiting() : Result<T> {
            return WaitingResult()
        }
    }
}