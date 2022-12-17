package com.submission.storyapplication.core.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.domain.repoInterface.IAllStoriesRepository
import com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase
import kotlinx.coroutines.flow.Flow

class AllStoriesInteractor(val storiesRepository: IAllStoriesRepository):
    AllStoriesUseCase {
    override fun get_all_stories(): LiveData<PagingData<Stories>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                storiesRepository.getPagingSource()
            }
        ).liveData
    }

    override fun getAllStories(): Flow<PagingData<Stories>> = storiesRepository.getPagingSourceFlow()
}