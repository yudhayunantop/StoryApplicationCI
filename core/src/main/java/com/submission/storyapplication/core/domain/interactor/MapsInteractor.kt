package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.repoInterface.IMapsRepository
import com.submission.storyapplication.core.domain.useCase.MapsUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

class MapsInteractor(val mapsRepository: IMapsRepository):
    MapsUseCase {
    override suspend fun get_all_stories_location(token: String): Flow<Resources<List<StoriesEntity>>> {
        return mapsRepository.get_all_stories_location(token)
    }
}