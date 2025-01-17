package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R
import com.mydailylift.app.adapters.ExercisesAdapter
import com.mydailylift.app.data.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.navigation.fragment.findNavController


class ExercisesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddExercise: FloatingActionButton
    private val exercisesAdapter = ExercisesAdapter(onDetailsClick = { exercise ->
        navigateToExerciseDetails(exercise)
    })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercises, container, false)

        // Initialize RecyclerView and FAB
        recyclerView = rootView.findViewById(R.id.recycler_view)
        fabAddExercise = rootView.findViewById(R.id.fab_add_exercise)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = exercisesAdapter

        // Fetch exercises
        fetchExercises()

        // Handle Add Exercise FAB
        fabAddExercise.setOnClickListener {
            showAddExerciseDialog()
        }

        return rootView
    }

    private fun fetchExercises() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            val exercises = database.exerciseDao().getAllExercises()

            withContext(Dispatchers.Main) {
                if (exercises.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "No exercises found. Click '+' to add one!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    exercisesAdapter.submitList(exercises)
                    Log.d("ExercisesFragment", "Fetched exercises: $exercises")
                }
            }
        }
    }

    private fun showAddExerciseDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_exercise, null)

        val exerciseNameInput = dialogView.findViewById<EditText>(R.id.exercise_name)
        val bodyPartSpinner = dialogView.findViewById<Spinner>(R.id.body_part_spinner)
        val equipmentSpinner = dialogView.findViewById<Spinner>(R.id.equipment_spinner)
        val secondaryMusclesInput = dialogView.findViewById<EditText>(R.id.secondary_muscles)

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Exercise")
            .setPositiveButton("Save") { _, _ ->
                val exerciseName = exerciseNameInput.text.toString()
                val bodyPart = bodyPartSpinner.selectedItem.toString()
                val equipment = equipmentSpinner.selectedItem.toString()
                val secondaryMuscles = secondaryMusclesInput.text.toString()

                if (exerciseName.isEmpty()) {
                    Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    saveExercise(
                        Exercise(
                            id = System.currentTimeMillis().toString(),
                            name = exerciseName,
                            bodyPart = bodyPart,
                            target = bodyPart, // Placeholder
                            equipment = equipment,
                            secondaryMuscles = secondaryMuscles,
                            instructions = "Instructions coming soon"
                        )
                    )
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun saveExercise(exercise: Exercise) {
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            database.exerciseDao().insertAll(exercise)

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Exercise added: ${exercise.name}", Toast.LENGTH_SHORT).show()
                fetchExercises()
            }
        }
    }

    private fun navigateToExerciseDetails(exercise: Exercise) {
        val action = ExercisesFragmentDirections.actionExercisesFragmentToExerciseDetailsFragment(exercise)
        findNavController().navigate(action)

        Log.d("NavigationDebug", "Navigating to ExerciseDetailsFragment for exercise: ${exercise.name}")
    }

}
