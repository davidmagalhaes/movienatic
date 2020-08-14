package com.davidmag.movienatic.presentation.common


interface PresentationObject {
    companion object {
        const val VIEWTYPE_CONTENT = 0
        const val VIEWTYPE_EMPTY = -1
        const val VIEWTYPE_WAITING = -2
        const val VIEWTYPE_ERROR = -3
    }

    val viewType: Int
    val exception: Throwable?
        get() = null
}