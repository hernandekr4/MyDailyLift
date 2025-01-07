package com.mydailylift.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routine_table")
    fun getAllRoutines(): List<Routine>

    @Insert
    fun insertRoutine(routine: Routine)

    @Query("DELETE FROM routine_table WHERE id = :routineId")
    fun deleteRoutine(routineId: Int)

    @Query("UPDATE routine_table SET name = :name WHERE id = :routineId")
    fun updateRoutine(routineId: Int, name: String)
}
