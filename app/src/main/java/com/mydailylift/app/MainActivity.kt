package com.mydailylift.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Set up the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

            Log.d("MainActivityDebug", "NavHostFragment successfully initialized")

        val navController = navHostFragment.navController

        // Set up BottomNavigationView with NavController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // Add navigation listener for debugging
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            Log.d("NavigationDebug", "Navigated to: ${destination.label}")
            arguments?.let {
                Log.d("NavigationDebug", "Arguments: $arguments")
            }
        }
    }
}
