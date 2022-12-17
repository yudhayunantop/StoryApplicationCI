package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.repoInterface.ILoginRepository
import com.submission.storyapplication.core.domain.useCase.LoginUseCase
import com.submission.storyapplication.core.data.remote.response.LoginResponse
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

class LoginInteractor(val loginRepository: ILoginRepository):
    LoginUseCase {
    override suspend fun login(email: String, password: String): Flow<Resources<LoginResponse.login>> {
        return loginRepository.login(email, password)
    }
}