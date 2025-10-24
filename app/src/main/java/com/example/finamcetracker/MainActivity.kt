package com.example.finamcetracker

import android.app.ComponentCaller
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.Activities.LoginPageActivity
import com.example.finamcetracker.Activities.SettingsActivity
import com.example.finamcetracker.Activities.ViewBudgetEntryActivity
import com.example.finamcetracker.CostumeViews.BudgetEntryView
import com.example.finamcetracker.models.BudgetEntry
import com.example.finamcetracker.models.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.sql.Date

class MainActivity : AppCompatActivity() {
    lateinit var welcomeText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        handleNavbar()
        getRefferences()
        verifyUserIsSignedIn()
        setWelcomeMessage()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        handleBudgetEntries()
        setWelcomeMessage()
    }

    fun getRefferences(){
        welcomeText = findViewById(R.id.WelcomeText)
    }

    fun setWelcomeMessage(){
        val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "")
        val welcomeMessage = "Hi $username"
        welcomeText.text = welcomeMessage
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
                R.id.nav_budget_list ->{
                    Log.d("testing clicks", "list")
                    true
                }

                R.id.nav_settings ->{
                    intent = Intent(this, SettingsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    true
                }
                else->false;
            }


        }

    }

    fun verifyUserIsSignedIn(){
        val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)

        val username = prefs.getString("username", null)
        if(username == null){
            val intent = Intent(this, LoginPageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
        }
    }
}