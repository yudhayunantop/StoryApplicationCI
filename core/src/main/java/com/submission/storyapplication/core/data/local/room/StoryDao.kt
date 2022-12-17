package com.submission.storyapplication.core.data.local.room

import androidx.room.*
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.domain.model.Stories
import com.submission.storyapplication.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStoriesFavorite(): Flow<List<StoriesEntity>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    suspend fun insertFavorite(stories: StoriesEntity)

    @Delete
    suspend fun deleteFavorite(stories: StoriesEntity)

    @Query("SELECT EXISTS(SELECT * FROM stories WHERE id = :id)")
    fun isRowIsExist(id : String) : Boolean
}