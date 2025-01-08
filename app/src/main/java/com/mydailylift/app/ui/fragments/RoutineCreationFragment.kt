package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R
import com.mydailylift.app.adapters.ExercisesAdapter
import com.mydailylift.app.data.DailyLiftDatabase
import com.mydailylift.app.data.Routine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class RoutineCreationFragment : Fragment() {

    private lateinit var routineNameInput: EditText
    private lateinit var addExercisesButton: Button
    private lateinit var saveRoutineButton: Button
    private lateinit var recyclerView: RecyclerView
    private val addedExercisesAdapter = ExercisesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_routine_creation, container, false)

        // Initialize views
        routineNameInput = rootView.findViewById(R.id.routine_name_input)
        addExercisesButton = rootView.findViewById(R.id.add_exercises_button)
        saveRoutineButton = rootView.findViewById(R.id.save_routine_button)
        recyclerView = rootView.findViewById(R.id.added_exercises_recycler_view)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = addedExercisesAdapter

        // Add Exercises Button
        addExercisesButton.setOnClickListener {
            Toast.makeText(requireContext(), "Add Exercises feature coming soon", Toast.LENGTH_SHORT).show()
        }

        // Save Routine Button
        saveRoutineButton.setOnClickListener {
            saveRoutine()
        }

        return rootView
    }

    private fun saveRoutine() {
        val routineName = routineNameInput.text.toString()
        if (routineName.isEmpty()) {
            Toast.makeText(requireContext(), "Routine name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            val newRoutine = Routine(
                name = routineName,
                createdAt = Date().toString(),
                exercises = emptyList() // Placeholder for exercises
            )
            database.routineDao().insertRoutine(newRoutine)

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Routine '$routineName' saved!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp() // Navigate back to RoutinesFragment
            }
        }
    }
}
