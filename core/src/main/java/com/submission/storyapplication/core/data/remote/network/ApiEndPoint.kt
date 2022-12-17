package com.submission.storyapplication.core.data.remote.network

import com.submission.storyapplication.core.data.remote.response.AllStoriesResponse
import com.submission.storyapplication.core.data.remote.response.LoginResponse
import com.submission.storyapplication.core.data.remote.response.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiEndPoint {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Response

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : LoginResponse

    @Multipart
    @POST("stories")
    suspend fun add_story(
        @Header("Authorization") token:String,
        @Part("description") description: RequestBody,
        @Part photo: MultipartBody.Part
    ) : Response

    @GET("stories")
    suspend fun get_all_stories(
    @Header("Authorization") token:String,
    @Query("page") page: Int,
    @Query("size") size: Int
    ) : AllStoriesResponse

    @GET("stories?location=1")
    suspend fun get_all_stories_location(
        @Header("Authorization") token:String
    ) : AllStoriesResponse
}