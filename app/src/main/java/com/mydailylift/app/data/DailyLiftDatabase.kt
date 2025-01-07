package com.mydailylift.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Routine::class, Exercise::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) // Register Converters here
abstract class DailyLiftDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    abstract fun exerciseDao(): ExerciseDao
}
