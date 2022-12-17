package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MapsRepository(val remoteDataSource: RemoteDataSource):
    IMapsRepository {
    override suspend fun get_all_stories_location(token: String) : Flow<Resources<List<StoriesEntity>>> {
        return flow {
            remoteDataSource.get_all_stories_location(token).collect { response ->
                when(response) {
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