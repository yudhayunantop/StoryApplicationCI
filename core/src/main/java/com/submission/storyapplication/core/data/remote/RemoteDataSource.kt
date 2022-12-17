package com.submission.storyapplication.core.data.remote

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.remote.network.ApiEndPoint
import com.submission.storyapplication.core.data.remote.network.ApiResponse
import com.submission.storyapplication.core.data.remote.response.LoginResponse
import com.submission.storyapplication.core.data.remote.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RemoteDataSource(private val apiEndPoint: ApiEndPoint) {
    suspend fun login(email:String, password:String): Flow<ApiResponse<LoginResponse.login>>{
        return flow {
            try {
                val responseLogin=apiEndPoint.login(email, password)
                if (responseLogin.error == false){
                    if (responseLogin.loginResult == null){
                        emit(ApiResponse.Empty)
                    }else{
                        emit(ApiResponse.Success(data=responseLogin.loginResult))
                    }
                }else if (responseLogin.error==true){
                    emit(ApiResponse.Error(errorMessage= responseLogin.message.toString()))
                }
            }catch (E:Exception){
                emit(ApiResponse.Error(errorMessage= E.message.toString()))
            }
        }
    }
    suspend fun register(name:String, email:String, password: String): Flow<ApiResponse<Response>>{
        return flow{
            try {
                val responseRegister=apiEndPoint.register(name=name, email=email, password=password)
                if (responseRegister.error == false){
                    emit(ApiResponse.Success(data=responseRegister))
                }else if (responseRegister.error==true){
                    emit(ApiResponse.Error(errorMessage= responseRegister.message.toString()))
                }
            }catch (E:Exception){
                emit(ApiResponse.Error(errorMessage= E.message.toString()))
            }
        }
    }
    suspend fun add_story(token: String, description: RequestBody, photo: MultipartBody.Part): Flow<ApiResponse<Response>>{
        return flow{
            try {
                val responseAddStory=apiEndPoint.add_story(token=token, description=description, photo=photo)
                if (responseAddStory.error == false){
                    emit(ApiResponse.Success(data=responseAddStory))
                }else if (responseAddStory.error==true){
                    emit(ApiResponse.Error(errorMessage= responseAddStory.message.toString()))
                }
            }catch (E:Exception){
                emit(ApiResponse.Error(errorMessage= E.message.toString()))
            }
        }
    }

    suspend fun getAllStories(token: String, position: Int, loadSize: Int) : Flow<ApiResponse<List<StoriesEntity>>> {
        return flow {
            try {
                val responseAllStories =  apiEndPoint.get_all_stories(token = token, page = position, size = loadSize)
                if (responseAllStories.error == false) {
                    if (responseAllStories.listStory.isNullOrEmpty()){
                        emit(ApiResponse.Empty)
                    }else{
                        emit(ApiResponse.Success(data = responseAllStories.listStory as List<StoriesEntity>))
                    }
                } else {
                    emit(ApiResponse.Error(errorMessage= responseAllStories.message.toString()))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(errorMessage= e.message.toString()))
            }
        }
    }

    suspend fun get_all_stories_location(token: String): Flow<ApiResponse<List<StoriesEntity>>>{
        return flow{
            try {
                val responseAllStoriesLocation=apiEndPoint.get_all_stories_location(token)
                if (responseAllStoriesLocation.error == false){
                    if (responseAllStoriesLocation.listStory.isNullOrEmpty()){
                        emit(ApiResponse.Empty)
                    }else {
                        emit(ApiResponse.Success(data = responseAllStoriesLocation.listStory as List<StoriesEntity>))
                    }
                }else if (responseAllStoriesLocation.error==true){
                    emit(ApiResponse.Error(errorMessage= responseAllStoriesLocation.message.toString()))
                }
            }catch (E:Exception){
                emit(ApiResponse.Error(errorMessage= E.message.toString()))
            }
        }
    }
}