package com.submission.storyapplication.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.submission.storyapplication.core.data.local.StoriesPagingSource
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.local.room.StoriesDatabase
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.Response
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoriesRepository(
    private val storiesDatabase: StoriesDatabase,
    private val remoteDataSource: RemoteDataSource
) : IAllStoriesRepository {
    override fun getPagingSource() =
        StoriesPagingSource(remoteDataSource)

    override fun getPagingSourceFlow(): Flow<PagingData<Stories>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoriesPagingSource(remoteDataSource)
            }
        ).flow
    }

    override suspend fun addStories(
        token: String,
        description: RequestBody,
        photo: MultipartBody.Part
    )
    : Flow<Resources<Response>>  {
      return flow {
          remoteDataSource.add_story(
              token,
              description,
              photo
          ).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Resources.Success(response.data))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resources.Error("Empty"))
                    }
                    is ApiResponse.Error -> {
                        emit(Resources.Error(response.errorMessage))
                    }
                }
          }
      }
    }
}