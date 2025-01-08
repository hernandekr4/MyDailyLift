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
        val rootView = inflater.inflate(R.layout.fragment_exercises, container, false)

        // Initialize views
        recyclerView = rootView.findViewById(R.id.recycler_view)
        createRoutineButton = rootView.findViewById(R.id.create_routine_button)

        // Setup RecyclerView
        setupRecyclerView()

        // Fetch exercises and display them
        fetchExercises()

        // Set button click listener
        createRoutineButton.setOnClickListener {
            Toast.makeText(requireContext(), "Create Routine button clicked", Toast.LENGTH_SHORT).show()
            Log.d("ExercisesFragment", "Create Routine button clicked")
            // TODO: Implement navigation to Create Routine screen
        }

        return rootView
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
