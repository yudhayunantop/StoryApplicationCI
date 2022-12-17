package com.submission.storyapplication.favorit

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.FavoriteUseCase

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    fun getAllFavorite() = favoriteUseCase.getAllStoriesFavorite()
}