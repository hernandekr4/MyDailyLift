package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R
import com.mydailylift.app.adapters.ExercisesAdapter
import com.mydailylift.app.data.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExercisesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var createRoutineButton: Button
    private val exercisesAdapter = ExercisesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentDebug", "Inflating layout for ExercisesFragment")
        return inflater.inflate(R.layout.fragment_exercises, container, false)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = exercisesAdapter
        Log.d("ExercisesFragment", "RecyclerView setup completed.")
    }

    private fun fetchExercises() {
        Log.d("ExercisesFragment", "Fetching exercises...")
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            val exercises = database.exerciseDao().getAllExercises()

            withContext(Dispatchers.Main) {
                if (exercises.isEmpty()) {
                    Log.d("ExercisesFragment", "No exercises found.")
                    Toast.makeText(requireContext(), "No exercises available!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("ExercisesFragment", "Fetched exercises: $exercises")
                    exercisesAdapter.submitList(exercises)
                }
            }
        }
    }
}
