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

class ExerciseSelectionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var createExerciseButton: Button
    private val exercisesAdapter = ExercisesAdapter()

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
}
