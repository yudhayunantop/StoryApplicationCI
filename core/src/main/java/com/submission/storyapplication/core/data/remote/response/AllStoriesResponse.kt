package com.submission.storyapplication.core.data.remote.response

import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import java.io.Serializable

data class AllStoriesResponse(
    val error: Boolean?,
    val message: String?,
    val listStory: List<StoriesEntity>?
):Serializable