package com.example.finamcetracker

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.CostumeViews.BudgetEntryView
import com.example.finamcetracker.models.BudgetEntry
import com.example.finamcetracker.models.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.sql.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        handleNavbar()
        handleBudgetEntries()

        var user = User("testUser", "password")

        user.save(this)
        User.logUsers(this);
    }

    fun handleBudgetEntries(){
        val testBudgetEntry = BudgetEntry(20.0,"test", Date(1))
        val budgetEntryView = BudgetEntryView(this, attrs = null, entry = testBudgetEntry);
        val entriesList = findViewById<LinearLayout>(R.id.budegetEntriesPreviewLayout)
        entriesList.addView(budgetEntryView)
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