package com.submission.storyapplication

import android.app.Application
import com.submission.storyapplication.core.di.databaseModul
import com.submission.storyapplication.core.di.networkModule
import com.submission.storyapplication.core.di.repositoryModule
import com.submission.storyapplication.di.interactorModul
import com.submission.storyapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StoryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@StoryApplication)
            modules(listOf(
                networkModule,
                repositoryModule,
                viewModelModule,
                databaseModul,
                interactorModul
            ))
        }
    }
}