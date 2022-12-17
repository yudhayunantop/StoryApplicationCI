package com.submission.storyapplication.login

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.LoginUseCase

class LoginViewModel (val useCase: LoginUseCase): ViewModel(){
    suspend fun login(email:String, password:String)= useCase.login(email, password)
}