package com.submission.storyapplication.core.domain.repoInterface

import androidx.paging.PagingData
import com.submission.storyapplication.core.data.local.StoriesPagingSource
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.response.Response
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IAllStoriesRepository {
    fun getPagingSource(): StoriesPagingSource

    fun getPagingSourceFlow(): Flow<PagingData<Stories>>

    suspend fun addStories(
            token:String,
            description: RequestBody,
            photo: MultipartBody.Part
    ): Flow<Resources<Response>>
}