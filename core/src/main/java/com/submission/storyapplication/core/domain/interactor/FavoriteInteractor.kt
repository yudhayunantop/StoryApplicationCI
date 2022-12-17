package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.domain.repoInterface.IFavoriteRepository
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor (val favoriteRepository: IFavoriteRepository):
    FavoriteUseCase {
    override fun getAllStoriesFavorite(): Flow<Resources<List<Stories>>> {
        return favoriteRepository.getAllStoriesFavorite()
    }
    override suspend fun insertFavorite(stories: Stories):Flow<Resources<String>>{
        return favoriteRepository.insertFavorite(stories)
    }
    override suspend fun  deleteFavorite(stories: Stories): Flow<Resources<String>> {
        return favoriteRepository.deleteFavorite(stories)
    }

    override fun isRowExist(id: String): Flow<Resources<Boolean>> {
        return favoriteRepository.isRowExist(id)
    }
}