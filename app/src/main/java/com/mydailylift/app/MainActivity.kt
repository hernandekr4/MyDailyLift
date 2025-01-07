package com.mydailylift.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mydailylift.app.ui.fragments.ProgressFragment
import com.mydailylift.app.ui.fragments.SettingsFragment
import com.mydailylift.app.ui.fragments.HomeScreenFragment
import com.mydailylift.app.ui.fragments.ExercisesFragment
import com.mydailylift.app.ui.fragments.RoutinesFragment



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default fragment to HomeScreenFragment
        loadFragment(HomeScreenFragment())

        // Handle navigation item selection
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeScreenFragment())
                R.id.nav_exercises -> loadFragment(ExercisesFragment())
                R.id.nav_routines -> loadFragment(RoutinesFragment())
                R.id.nav_progress -> loadFragment(ProgressFragment())
                R.id.nav_settings -> loadFragment(SettingsFragment())
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}
