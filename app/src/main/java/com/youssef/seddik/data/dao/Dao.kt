package com.youssef.seddik.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>)

    @Query("SELECT * FROM abilities WHERE page = :page")
    suspend fun getAbilitiesByPage(page: Int): List<AbilityEntity>

    @Query("SELECT * FROM abilities")
    suspend fun getAbilities(): List<AbilityEntity>
}