package com.sample.dishes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@Entity(tableName = "home_model")
@JsonClass(generateAdapter = true)

data class HomeModelEntity(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val image_url: String,
        val more_images: List<String>,
        val name: String,
        val share_link: String,
        val short_desc: String,
        val wiki_link: String
)