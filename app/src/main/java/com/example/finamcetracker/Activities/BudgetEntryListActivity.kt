package com.example.finamcetracker.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.CostumeViews.BudgetEntryView
import com.example.finamcetracker.R
import com.example.finamcetracker.models.BudgetEntry
import com.example.finamcetracker.models.BudgetHistory
import com.example.finamcetracker.models.User
import com.google.android.material.bottomnavigation.BottomNavigationView

class BudgetEntryListActivity : AppCompatActivity() {
    lateinit var budgetList : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.budget_entry_list)
        handleNavbar()
        getRefferences()
        handleBudgetEntries(BudgetHistory.entries)

    }

    fun handleBudgetEntries(list: List<BudgetEntry>){

        budgetList.removeAllViews()
        for (entry in list){
            val budgetEntryView = BudgetEntryView(this, attrs = null, entry = entry);
            budgetList.addView(budgetEntryView)
        }

    }

    fun handleFilterChanged(){

    }
    fun getRefferences(){
        budgetList = findViewById(R.id.BudgetListView)
    }
    fun handleNavbar(){
        val navBar = findViewById<BottomNavigationView>(R.id.navBar)

        navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home ->{
                    finish()
                    true
                }

                R.id.nav_settings ->{
                    finish()
                    intent = Intent(this, SettingsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    true
                }
                else->false;
            }
        }
    }
}