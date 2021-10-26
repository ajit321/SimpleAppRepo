package com.sample.dishes.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {
    @TypeConverter
    fun fromCategoryString(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromCategoryArrayList(list: List<String>): String {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(list, type)
    }
}