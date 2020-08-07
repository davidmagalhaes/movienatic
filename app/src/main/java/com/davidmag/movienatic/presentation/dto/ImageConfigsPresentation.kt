package com.davidmag.movienatic.presentation.dto

import com.davidmag.movienatic.domain.model.ImageConfigs
import com.davidmag.movienatic.presentation.common.PresentationObject

data class ImageConfigsPresentation(
    override val viewType: Int,
    val imageConfigs : ImageConfigs,
    override val exception: Throwable? = null
) : PresentationObject