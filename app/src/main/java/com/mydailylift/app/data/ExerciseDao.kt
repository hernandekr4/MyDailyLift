package com.mydailylift.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise_table")
    suspend fun getAllExercises(): List<Exercise>

    @Insert
    suspend fun insertAll(vararg exercises: Exercise)
}
