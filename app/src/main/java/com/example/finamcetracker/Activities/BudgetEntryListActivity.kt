package com.example.finamcetracker.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
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
    lateinit var filterDropdown : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.budget_entry_list)
        handleNavbar()
        getRefferences()
        handleBudgetEntries(BudgetHistory.entries)
        handleFilterChanged()
    }

    fun handleBudgetEntries(list: List<BudgetEntry>){

        budgetList.removeAllViews()
        for (entry in list){
            val budgetEntryView = BudgetEntryView(this, attrs = null, entry = entry);
            budgetEntryView.setDeleteCallback {
                budgetList.removeView(budgetEntryView)
            }
            budgetList.addView(budgetEntryView)
        }

    }

    fun handleFilterChanged(){
        filterDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selected = parent.getItemAtPosition(position).toString()
                when (selected){

                    "Latest" ->{
                        handleBudgetEntries(BudgetHistory.sortByDate().toList().reversed())
                    }
                    "Oldest" ->{
                        handleBudgetEntries(BudgetHistory.sortByDate().toList())
                    }
                    "Highest" ->{
                            handleBudgetEntries(BudgetHistory.sortByValue().toList().reversed())
                    }
                    "Lowest" ->{
                        handleBudgetEntries(BudgetHistory.sortByValue().toList())
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }
    }
    fun getRefferences(){
        budgetList = findViewById(R.id.BudgetListView)
        filterDropdown = findViewById(R.id.FilterDropDown)
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