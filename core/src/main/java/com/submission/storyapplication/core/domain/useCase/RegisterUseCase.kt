package com.submission.storyapplication.core.domain.useCase

import com.submission.storyapplication.core.data.remote.response.Response
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    suspend fun register(name: String, email: String, password: String): Flow<Resources<Response>>
}