package com.submission.storyapplication.detail

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase
import com.submission.storyapplication.core.utils.DataMapper

class DetailViewModel(val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    suspend fun addFavorite(stories: Stories)= favoriteUseCase.insertFavorite(stories)
    fun isRowExist(id:String) = favoriteUseCase.isRowExist(id = id)
    suspend fun delete(stories: Stories)= favoriteUseCase.deleteFavorite(stories)
}