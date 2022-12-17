package com.submission.storyapplication.core.domain.repoInterface

import com.submission.storyapplication.core.data.remote.response.LoginResponse
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
 suspend fun login(email: String, password: String): Flow<Resources<LoginResponse.login>>
}