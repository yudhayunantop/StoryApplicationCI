package com.submission.storyapplication.core.data.remote.response

data class LoginResponse(
    val error: Boolean?,
    val message: String?,
    val loginResult: login?
){
    data class login(
        val userId: String?,
        val name: String?,
        val token: String?
    )
}
