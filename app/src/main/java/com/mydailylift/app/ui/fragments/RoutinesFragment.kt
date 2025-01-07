package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mydailylift.app.DailyLiftApplication
import com.mydailylift.app.R
import com.mydailylift.app.data.Routine
import com.mydailylift.app.data.DailyLiftDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoutinesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddRoutine: FloatingActionButton
    private val routinesAdapter = RoutinesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_routines, container, false)

        // Initialize views
        recyclerView = rootView.findViewById(R.id.recycler_view)
        fabAddRoutine = rootView.findViewById(R.id.fab_add_routine)

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
    }

    private fun fetchRoutines() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = (requireActivity().application as DailyLiftApplication).database
            val routines = database.routineDao().getAllRoutines()

            withContext(Dispatchers.Main) {
                routinesAdapter.submitList(routines)
            }
        }
    }

    private fun addNewRoutine() {
        // TODO: Replace with actual dialog or navigation to create routine screen
        Toast.makeText(requireContext(), "Add Routine clicked", Toast.LENGTH_SHORT).show()
    }
}
