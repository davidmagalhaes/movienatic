package com.davidmag.movienatic.presentation.common

data class GenericPresentationObject<T>(
    override val viewType: Int,
    val value : T? = null,
    override val exception: Throwable? = null
) : PresentationObject