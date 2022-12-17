package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MapsUseCase {
    suspend fun get_all_stories_location(token: String): Flow<Resources<List<StoriesEntity>>>
}