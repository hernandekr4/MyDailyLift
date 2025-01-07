package com.mydailylift.app.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseModule {

    @Volatile
    private var INSTANCE: DailyLiftDatabase? = null

    fun getDatabase(context: Context): DailyLiftDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                DailyLiftDatabase::class.java,
                "daily_lift_database"
            )
                .fallbackToDestructiveMigration()
                .addCallback(PrepopulateCallback(context)) // Attach the callback here
                .build()
            INSTANCE = instance
            instance
        }
    }

    private class PrepopulateCallback(
        private val context: Context
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val exercises = DataLoader.loadExercises(context)
                    Log.d("DatabaseModule", "Loaded exercises for prepopulation: $exercises")

                    // Attempt to insert exercises into the database
                    getDatabase(context).exerciseDao().insertAll(*exercises.toTypedArray())

                    // Verify insertion
                    val insertedExercises = getDatabase(context).exerciseDao().getAllExercises()
                    Log.d("DatabaseModule", "Inserted exercises in database: $insertedExercises")
                } catch (e: Exception) {
                    Log.e("DatabaseModule", "Error prepopulating database", e)
                }
            }
        }
    }
}
