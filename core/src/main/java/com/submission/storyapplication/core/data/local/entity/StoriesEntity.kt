package com.submission.storyapplication.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "stories")
data class StoriesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name="description")
    val description: String?,
    @ColumnInfo(name="photoUrl")
    val photoUrl: String?,
    @ColumnInfo(name="createdAt")
    val createdAt: String?,
    @ColumnInfo(name="lat")
    val lat: Float?,
    @ColumnInfo(name="lon")
    val lon: Float?
): Serializable
