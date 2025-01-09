package com.mydailylift.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.R
import com.mydailylift.app.adapters.WeeklyOverviewAdapter

class HomeScreenFragment : Fragment() {

    private lateinit var weeklyOverview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FragmentDebug", "Inflating layout for HomeScreenFragment")
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    private fun setupWeeklyOverview() {
        val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val adapter = WeeklyOverviewAdapter(weekDays) { day ->
            // Handle click for day selection
            // TODO: Navigate or show details for selected day
            println("Clicked on day: $day")

        }

        weeklyOverview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        weeklyOverview.adapter = adapter
    }
}
