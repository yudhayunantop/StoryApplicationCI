package com.submission.storyapplication.core.utils

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories

object DataMapper {
    fun mapStoriesEntitytoStories(input: List<StoriesEntity>): List<Stories> =
        input.map {
            Stories(
                id = it.id,
                name = it.name,
                description = it.description,
                photoUrl = it.photoUrl,
                createdAt = it.createdAt,
                lat = it.lat,
                lon = it.lon
            )
        }

    fun mapStoriesToStoriesEntity(input: Stories) = StoriesEntity(
        id = input.id,
        name = input.name,
        description = input.description,
        photoUrl = input.photoUrl,
        createdAt = input.createdAt,
        lat = input.lat,
        lon = input.lon
    )
}