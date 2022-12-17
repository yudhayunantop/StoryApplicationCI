package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {
    fun getAllStoriesFavorite(): Flow<Resources<List<Stories>>>
    suspend fun insertFavorite(stories: Stories): Flow<Resources<String>>
    suspend fun  deleteFavorite(stories: Stories): Flow<Resources<String>>
    fun isRowExist(id:String): Flow<Resources<Boolean>>
}