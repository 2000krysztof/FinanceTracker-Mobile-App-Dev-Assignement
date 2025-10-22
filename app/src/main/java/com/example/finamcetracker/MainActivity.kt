package com.example.finamcetracker

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        handleNavbar()


    }


    fun handleNavbar(){
        val navBar = findViewById<BottomNavigationView>(R.id.navBar)

        navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home ->{
                    Log.d("testing clicks", "home")
                    true
                }

                R.id.nav_budget_list ->{
                    Log.d("testing clicks", "list")
                    true
                }

                R.id.nav_settings ->{
                    Log.d("testing clicks", "settings")
                    true
                }
                else->false;
            }


        }

    }
}