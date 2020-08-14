package com.davidmag.movienatic.presentation.common

class GenericPresentationObject<T>(
    override val viewType: Int,
    val value : T? = null,
    override val exception: Throwable? = null
) : PresentationObject