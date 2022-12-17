package com.submission.storyapplication.core.data.repository

import com.submission.storyapplication.core.data.remote.RemoteDataSource
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.Response
import com.submission.storyapplication.core.domain.repoInterface.IRegisterRepository
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterRepository(val remoteDataSource: RemoteDataSource):
    IRegisterRepository {
    override suspend fun register(name: String, email: String, password: String) : Flow<Resources<Response>> {
        return flow {
            emit(Resources.Loading())
            remoteDataSource.register(name, email, password).collect {
                when(it){
                    is ApiResponse.Success -> {
                        emit(Resources.Success(it.data))
                    }
                    is ApiResponse.Error -> {
                        emit(Resources.Error(it.errorMessage))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resources.Error("Empty"))
                    }
                }
            }
        }
    }
}