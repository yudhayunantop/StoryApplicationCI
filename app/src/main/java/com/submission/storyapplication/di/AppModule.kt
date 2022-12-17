package com.submission.storyapplication.di

import com.submission.storyapplication.main.MainViewModel
import com.submission.storyapplication.add.AddStoriesViewModel
import com.submission.storyapplication.core.domain.interactor.*
import com.submission.storyapplication.core.domain.useCase.*
import com.submission.storyapplication.detail.DetailViewModel
import com.submission.storyapplication.login.LoginViewModel
import com.submission.storyapplication.maps.MapsViewModel
import com.submission.storyapplication.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MapsViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { AddStoriesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val interactorModul = module {
    factory<LoginUseCase> {
        LoginInteractor(
            get()
        )
    }
    factory<RegisterUseCase> {
        RegisterInteractor(
            get()
        )
    }
    factory<MapsUseCase> {
        MapsInteractor(
            get()
        )
    }
    factory<AllStoriesUseCase> {
        AllStoriesInteractor(
            get()
        )
    }
    factory<AddStoriesUseCase> {
        AddStoriesInteractor(
            get()
        )
    }
    factory<FavoriteUseCase> {
        FavoriteInteractor(
            get()
        )
    }
}