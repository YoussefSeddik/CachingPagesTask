package com.youssef.seddik.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.youssef.seddik.data.dao.Dao
import com.youssef.seddik.data.dao.AbilityEntity

@Database(entities = [AbilityEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun abilityDao(): Dao
}