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
import com.example.finamcetracker.Activities.BudgetEntryListActivity
import com.example.finamcetracker.Activities.LoginPageActivity
import com.example.finamcetracker.Activities.SettingsActivity
import com.example.finamcetracker.Activities.ViewBudgetEntryActivity
import com.example.finamcetracker.CostumeViews.BudgetEntryView
import com.example.finamcetracker.models.BudgetEntry
import com.example.finamcetracker.models.BudgetHistory
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
        val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "")
        if(!(username == "" || username == null)){
            val user = User.getUserByName(this,username)
            if(user == null) {return}
            onUserIsPresent(user)

        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "")
        if(username == null){return}
        val user = User.getUserByName(this,username)
        if(user == null) { return}
        onUserIsPresent(user)
    }

    fun onUserIsPresent(user: User){
        setWelcomeMessage(user)
        handleBudgetEntries(user)
    }
    fun getRefferences(){
        welcomeText = findViewById(R.id.WelcomeText)
    }

    fun setWelcomeMessage(user: User){
        val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val username = prefs.getString("username", "")
        val welcomeMessage = "Hi $username"
        welcomeText.text = welcomeMessage
    }
    fun handleBudgetEntries(user: User){
        val budgetHistory = BudgetHistory()
        budgetHistory.loadFromFile(this, user.name)
        for (entry in budgetHistory.entries){
            val budgetEntryView = BudgetEntryView(this, attrs = null, entry = entry);
            val entriesList = findViewById<LinearLayout>(R.id.budegetEntriesPreviewLayout)
            entriesList.addView(budgetEntryView)
        }

    }

    fun handleNavbar(){
        val navBar = findViewById<BottomNavigationView>(R.id.navBar)

        navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_budget_list ->{
                    intent = Intent(this, BudgetEntryListActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
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