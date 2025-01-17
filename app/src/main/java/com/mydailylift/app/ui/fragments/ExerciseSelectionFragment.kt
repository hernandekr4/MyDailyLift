package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.mydailylift.app.R
import com.mydailylift.app.adapters.ExercisesAdapter
import com.mydailylift.app.data.Exercise

class ExerciseSelectionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var createExerciseButton: Button
    private val exercisesAdapter = ExercisesAdapter(onDetailsClick = { exercise ->
        navigateToExerciseDetails(exercise)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercise_selection, container, false)

        // Initialize views
        recyclerView = rootView.findViewById(R.id.exercises_recycler_view)
        searchInput = rootView.findViewById(R.id.search_exercises_input)
        createExerciseButton = rootView.findViewById(R.id.create_exercise_button)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = exercisesAdapter

        createExerciseButton.setOnClickListener {
            try {
                val action = ExerciseSelectionFragmentDirections.actionExerciseSelectionFragmentToCreateExerciseFragment()
                Log.d("NavigationDebug", "Navigating to CreateExerciseFragment with action: $action")
                findNavController().navigate(action)
            } catch (e: Exception) {
                Log.e("NavigationDebug", "Error navigating to CreateExerciseFragment", e)
            }
        }


        return rootView
    }

    private fun fetchExercises() {
        // TODO: Replace with actual data from the database
        val dummyExercises = listOf(
            Exercise(
                id = "1",
                name = "Push-Up",
                bodyPart = "Chest",
                target = "Pectorals",
                equipment = "None",
                secondaryMuscles = "Triceps, Shoulders",
                instructions = "Start in a plank position. Lower your body until your chest nearly touches the floor. Push yourself back up to the starting position."
            ),
            Exercise(
                id = "2",
                name = "Squat",
                bodyPart = "Legs",
                target = "Quadriceps",
                equipment = "None",
                secondaryMuscles = "Glutes, Hamstrings",
                instructions = "Stand with feet shoulder-width apart. Lower your body until your thighs are parallel to the floor. Return to the starting position."
            )
        )
        exercisesAdapter.submitList(dummyExercises)
    }

    private fun navigateToExerciseDetails(exercise: Exercise) {
        val bundle = Bundle().apply {
            putParcelable("exercise", exercise)
        }
        findNavController().navigate(R.id.exerciseDetailsFragment, bundle)
        Log.d("NavigationDebug", "Navigating to ExerciseDetailsFragment for exercise: ${exercise.name}")
    }

}
