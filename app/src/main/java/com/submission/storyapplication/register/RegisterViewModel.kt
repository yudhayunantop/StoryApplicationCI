package com.submission.storyapplication.register

import androidx.lifecycle.ViewModel
import com.submission.storyapplication.core.domain.useCase.RegisterUseCase
import com.submission.storyapplication.core.utils.Resources
import kotlinx.coroutines.flow.flow

class RegisterViewModel (val useCase: RegisterUseCase): ViewModel(){
    suspend fun register(name:String, email:String, password:String)= useCase.register(name,  email, password)
}