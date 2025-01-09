package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mydailylift.app.R

class RoutineDetailsFragment : Fragment() {

    private lateinit var routineNameTextView: TextView
    private lateinit var routineExercisesTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_routine_details, container, false)

        // Initialize views
        routineNameTextView = rootView.findViewById(R.id.routine_name_text)
        routineExercisesTextView = rootView.findViewById(R.id.routine_exercises_text)

        // Fetch routine data from arguments or viewModel (boilerplate logic for now)
        val routineName = arguments?.getString("routineName") ?: "Unknown Routine"
        val routineExercises = arguments?.getString("routineExercises") ?: "No exercises found"

        // Populate the views
        routineNameTextView.text = routineName
        routineExercisesTextView.text = routineExercises

        Toast.makeText(requireContext(), "Loaded routine: $routineName", Toast.LENGTH_SHORT).show()

        return rootView
    }
}
