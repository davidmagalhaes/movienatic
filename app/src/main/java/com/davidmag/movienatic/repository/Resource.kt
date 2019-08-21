package com.davidmag.movienatic.repository

data class Resource<out T>(val status: ResourceStatus, val data: T?, val error: Exception?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceStatus.SUCCESS, data, null)
        }

        fun <T> error(exception: Exception?, data: T?): Resource<T> {
            return Resource(ResourceStatus.ERROR, data, exception)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResourceStatus.LOADING, data, null)
        }
    }
}