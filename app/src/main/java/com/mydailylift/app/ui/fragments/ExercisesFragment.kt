package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.data.DailyLiftDatabase
import com.mydailylift.app.data.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.mydailylift.app.adapters.ExercisesAdapter
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R


class ExercisesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val exercisesAdapter = ExercisesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercises, container, false)

        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recycler_view)
        setupRecyclerView()

        // Fetch and display exercises
        fetchExercises()

        return rootView
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = exercisesAdapter
    }

    private fun fetchExercises() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            val exercises = database.exerciseDao().getAllExercises()

            withContext(Dispatchers.Main) {
                exercisesAdapter.submitList(exercises)
            }
        }
    }
}
