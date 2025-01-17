package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R
import com.mydailylift.app.data.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExerciseDetailsFragment : Fragment() {

    private val args: ExerciseDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercise_details, container, false)

        val exerciseName = rootView.findViewById<TextView>(R.id.exercise_name)
        val exerciseTarget = rootView.findViewById<TextView>(R.id.exercise_target)
        val exerciseEquipment = rootView.findViewById<TextView>(R.id.exercise_equipment)
        val exerciseSecondary = rootView.findViewById<TextView>(R.id.exercise_secondary)
        val deleteButton = rootView.findViewById<Button>(R.id.delete_button)
        val editButton = rootView.findViewById<Button>(R.id.edit_button)

        val exercise = args.exercise

        exerciseName.text = exercise.name
        exerciseTarget.text = "Target: ${exercise.target}"
        exerciseEquipment.text = "Equipment: ${exercise.equipment}"
        exerciseSecondary.text = "Secondary Muscles: ${exercise.secondaryMuscles}"

        deleteButton.setOnClickListener {
            deleteExercise(exercise)
        }

        editButton.setOnClickListener {
            Toast.makeText(requireContext(), "Edit feature coming soon", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun deleteExercise(exercise: Exercise) {
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            database.exerciseDao().deleteExercise(exercise.id)

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Deleted: ${exercise.name}", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp() // Navigate back to ExercisesFragment
            }
        }
    }



}
