package com.submission.storyapplication.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.domain.useCase.AllStoriesUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(useCase: AllStoriesUseCase) : ViewModel() {
//    val stories: LiveData<PagingData<Stories>> =
//        useCase.get_all_stories().cachedIn(viewModelScope)
    val storiesFlow: Flow<PagingData<Stories>> = useCase.getAllStories().cachedIn(viewModelScope)
}