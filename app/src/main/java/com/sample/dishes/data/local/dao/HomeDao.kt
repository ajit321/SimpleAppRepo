package com.sample.dishes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.dishes.data.local.entity.HomeModelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert
    suspend fun insertIntoTable(homeModel: HomeModelEntity)

    @Query("SELECT * FROM home_model")
    fun getDataFormTable(): HomeModelEntity

    @Query("SELECT * FROM home_model WHERE id = :noteId")
    fun getDataById(noteId: String): Flow<HomeModelEntity>
}