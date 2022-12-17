package com.submission.storyapplication.core.data.local

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.data.local.room.StoryDao

class LocalDataSource(private val storyDao: StoryDao) {
    fun getAllStoriesFavorite() = storyDao.getAllStoriesFavorite()
    suspend fun insertFavorite(stories: StoriesEntity) = storyDao.insertFavorite(stories)
    suspend fun deleteFavorite(stories: StoriesEntity) = storyDao.deleteFavorite(stories)
    fun isRowExist(id:String) = storyDao.isRowIsExist(id)
}