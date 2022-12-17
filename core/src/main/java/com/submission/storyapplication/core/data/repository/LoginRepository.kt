package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.LoginResponse
import com.submission.storyapplication.core.domain.repoInterface.ILoginRepository
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepository(val remoteDataSource: RemoteDataSource) :
    ILoginRepository {
    override suspend fun login(email: String, password: String): Flow<Resources<LoginResponse.login>> {
        return flow {
            emit(Resources.Loading())
            remoteDataSource.login(email, password).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Resources.Success(response.data))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resources.Error("Empty"))
                    }
                    is ApiResponse.Error -> {
                        emit(Resources.Error(response.errorMessage))
                    }
                }
            }

        }
    }

}