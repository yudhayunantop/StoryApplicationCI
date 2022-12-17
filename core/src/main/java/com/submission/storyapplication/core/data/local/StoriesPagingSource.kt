package com.submission.storyapplication.core.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.utils.DataMapper
import com.submission.storyapplication.core.utils.Preferences

class StoriesPagingSource(private val remoteDataSource: RemoteDataSource) :
    PagingSource<Int, Stories>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Stories> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val token = "Bearer ${Preferences.getToken()}"
//            val responseData = remoteDataSource.get_all_stories(
//                token,
//                position,
//                params.loadSize)
            var listStory = listOf<Stories>()
            remoteDataSource.getAllStories(
                token,
                position,
                params.loadSize
            ).collect { resource ->
                when (resource) {
                    is ApiResponse.Success -> {
                        listStory = DataMapper.mapStoriesEntitytoStories(resource.data)
//                        listStory = resource.data as List<StoriesEntity>
                    }
                    is ApiResponse.Error -> {}
                    is ApiResponse.Empty -> {}
                }

            }

            LoadResult.Page(
                data = listStory,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (listStory.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Stories>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
