package com.davidmag.movienatic.presentation.common

interface PresentationObject {
    companion object {
        const val DEFAULT_VIEWTYPE_CONTENT = 0
        const val DEFAULT_VIEWTYPE_WAITING = -1
        const val DEFAULT_VIEWTYPE_EMPTY = -2
        const val DEFAULT_VIEWTYPE_ERROR = -3
    }

    val viewType: Int
    val exception: Throwable?
        get() = null
}