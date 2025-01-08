package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R
import com.mydailylift.app.adapters.RoutinesAdapter
import com.mydailylift.app.data.Routine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.navigation.fragment.findNavController

class RoutinesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noRoutinesText: TextView
    private lateinit var fabAddRoutine: FloatingActionButton

    private val routinesAdapter = RoutinesAdapter { routine, action ->
        when (action) {
            "start" -> startRoutine(routine)
            "edit" -> editRoutine(routine)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_routines, container, false)

        // Initialize views
        recyclerView = rootView.findViewById(R.id.recycler_view)
        noRoutinesText = rootView.findViewById(R.id.no_routines_text)
        fabAddRoutine = rootView.findViewById(R.id.fab_add_routine)

        Log.d("RoutinesFragment", "Views initialized: RecyclerView, NoRoutinesText, FAB")

        // Setup RecyclerView
        setupRecyclerView()

        // Fetch and display routines
        fetchRoutines()

        // Handle Add Routine button click
        fabAddRoutine.setOnClickListener {
            addNewRoutine()
        }

        return rootView
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = routinesAdapter
        Log.d("RoutinesFragment", "RecyclerView and Adapter setup completed")
    }
    private fun fetchRoutines() {
        Log.d("RoutinesFragment", "Fetching routines from the database...")
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database

            // Insert a default routine for debugging if the database is empty
            if (database.routineDao().getAllRoutines().isEmpty()) {
                val debugRoutine = Routine(
                    name = "Debug Routine",
                    createdAt = "2025-01-08",
                    exercises = listOf("Push-Up", "Squat")
                )
                database.routineDao().insertRoutine(debugRoutine)
                Log.d("RoutinesFragment", "Inserted debug routine: $debugRoutine")
            }

            val routines = database.routineDao().getAllRoutines()

            withContext(Dispatchers.Main) {
                if (routines.isEmpty()) {
                    noRoutinesText.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    Log.d("RoutinesFragment", "No routines found in the database")
                } else {
                    noRoutinesText.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    routinesAdapter.submitList(routines)
                    Log.d("RoutinesFragment", "Fetched routines: $routines")
                }
            }
        }
    }


    private fun startRoutine(routine: Routine) {
        Toast.makeText(requireContext(), "Start Routine: ${routine.name}", Toast.LENGTH_SHORT).show()
        Log.d("RoutinesFragment", "Start Routine clicked for: ${routine.name}")
        // TODO: Implement navigation to the Start Routine screen
    }

    private fun editRoutine(routine: Routine) {
        Toast.makeText(requireContext(), "Edit Routine: ${routine.name}", Toast.LENGTH_SHORT).show()
        Log.d("RoutinesFragment", "Edit Routine clicked for: ${routine.name}")
        // TODO: Implement navigation to the Edit Routine screen
    }

    private fun addNewRoutine() {
        val action = RoutinesFragmentDirections.actionRoutinesFragmentToRoutineCreationFragment()
        Log.d("RoutinesFragment", "Navigating to Routine Creation screen")
        findNavController().navigate(action)
    }
}
