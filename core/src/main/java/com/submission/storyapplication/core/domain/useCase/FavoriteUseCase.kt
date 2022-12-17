package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllStoriesFavorite(): Flow<Resources<List<Stories>>>
    suspend fun insertFavorite(stories: Stories):Flow<Resources<String>>
    suspend fun  deleteFavorite(stories: Stories): Flow<Resources<String>>
    fun isRowExist(id:String): Flow<Resources<Boolean>>
}