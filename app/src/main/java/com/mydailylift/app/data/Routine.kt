package com.mydailylift.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_table")
data class Routine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val createdAt: String,
    val exercises: List<String> // Room will now handle this with the TypeConverter
)
