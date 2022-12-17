package com.submission.storyapplication.maps

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.MapsUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.flow

class MapsViewModel (val useCase: MapsUseCase): ViewModel(){
    suspend fun maps(token: String)=useCase.get_all_stories_location(token)
}