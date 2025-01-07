package com.mydailylift.app.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataLoader {

    fun loadExercises(context: Context): List<Exercise> {
        val jsonString = context.assets.open("exercises.json")
            .bufferedReader()
            .use { it.readText() }

        val listType = object : TypeToken<List<Exercise>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}
