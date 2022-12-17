package com.submission.storyapplication.core.domain.model

import java.io.Serializable

data class Stories(
    val id: String,
    val name: String?,
    val description: String?,
    val photoUrl: String?,
    val createdAt: String?,
    val lat: Float?,
    val lon: Float?
) : Serializable
