package com.example.finamcetracker.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.R
import androidx.core.content.edit
import com.google.android.material.bottomnavigation.BottomNavigationView


class SettingsActivity : AppCompatActivity() {
    lateinit var logOutButton : Button
    lateinit var wipeAccountButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.settings)
        getRefferences()

        handleNavbar()
    }

    fun handleNavbar(){
        val navBar = findViewById<BottomNavigationView>(R.id.navBar)

        navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home ->{
                    finish()
                    true
                }

                R.id.nav_budget_list ->{
                    finish()
                    intent = Intent(this, BudgetEntryListActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    true
                }
                else->false;
            }
        }
    }

    fun getRefferences(){
        logOutButton = findViewById(R.id.LogOutButton)
        wipeAccountButton = findViewById(R.id.WipeAccountButton)
    }

    fun initializeLogOutButton(){
        logOutButton.setOnClickListener {
            val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
            prefs.edit {
                putString("username", null)
            }
            finish()
        }
    }


}