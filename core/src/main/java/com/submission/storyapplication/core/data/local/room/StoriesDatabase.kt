package com.submission.storyapplication.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.submission.storyapplication.core.data.local.entity.StoriesEntity

@Database(
    entities = [StoriesEntity::class],
    version = 3,
    exportSchema = false
)
abstract class StoriesDatabase : RoomDatabase() {
    abstract fun storyDao (): StoryDao
}