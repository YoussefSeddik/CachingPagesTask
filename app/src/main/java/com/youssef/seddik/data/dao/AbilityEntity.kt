package com.youssef.seddik.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abilities")
data class AbilityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val url: String = "",
    val page: Int = 1
)