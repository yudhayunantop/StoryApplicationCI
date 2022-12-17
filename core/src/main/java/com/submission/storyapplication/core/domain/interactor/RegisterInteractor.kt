package com.submission.storyapplication.core.domain.interactor

import com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository
import com.submission.storyapplication.core.domain.useCase.RegisterUseCase
import com.submission.storyapplication.core.data.remote.response.Response
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow

class RegisterInteractor(val registerRepository: IRegisterRepository):
    RegisterUseCase {
    override suspend fun register(name: String, email: String, password: String): Flow<Resources<Response>> {
        return registerRepository.register(name, email, password)
    }
}