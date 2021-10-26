package com.sample.dishes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class HomeModelResponses(
        val id: Int,
        val image_url: String,
        val more_images: List<String>,
        val name: String,
        val share_link: String,
        val short_desc: String,
        val wiki_link: String
)



