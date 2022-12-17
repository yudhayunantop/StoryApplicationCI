package com.submission.storyapplication.add

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.AddStoriesUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoriesViewModel(val useCase: AddStoriesUseCase) : ViewModel() {
    suspend fun addStories(token: String, description: RequestBody, photo: MultipartBody.Part) = useCase.addStories(token, description, photo)
}