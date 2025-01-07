package com.mydailylift.app.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydailylift.app.CalendarAdapter
import com.mydailylift.app.R
import com.mydailylift.app.adapters.WeeklyOverviewAdapter
import com.mydailylift.app.data.DailyLiftDatabase

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var database: DailyLiftDatabase
    private lateinit var weeklyOverview: RecyclerView
    private lateinit var calendarRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        // Initialize Views
        weeklyOverview = findViewById(R.id.weekly_overview)
        calendarRecyclerView = findViewById(R.id.calendar_recycler_view)

        // Setup Weekly Overview
        setupWeeklyOverview()

        // Setup Calendar
        setupCalendar()
    }

    private fun setupWeeklyOverview() {
        val weekDays = generateCurrentWeekDays() // Generate the week's days
        val weeklyAdapter = WeeklyOverviewAdapter(weekDays) { day ->
            Log.d("HomeScreen", "Clicked on day: $day")
            // TODO: Handle click to show/set routines for the selected day
        }

        weeklyOverview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        weeklyOverview.adapter = weeklyAdapter
    }

    private fun setupCalendar() {
        val calendarAdapter = CalendarAdapter() { day, isPastDay ->
            if (isPastDay) {
                Log.d("HomeScreen", "Clicked on past day: $day")
                // TODO: Fetch and show past workouts
            } else {
                Log.d("HomeScreen", "Clicked on future day: $day")
                // TODO: Show dialog to add routines for future days
            }
        }

        calendarRecyclerView.layoutManager = GridLayoutManager(this, 7) // 7 columns for a week
        calendarRecyclerView.adapter = calendarAdapter
    }

    private fun generateCurrentWeekDays(): List<String> {
        // Generate list of the current week's days (e.g., "Mon", "Tue", ...)
        // TODO: Replace with actual date logic
        return listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    }
}