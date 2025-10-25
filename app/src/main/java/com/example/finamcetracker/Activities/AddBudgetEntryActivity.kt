package com.example.finamcetracker.Activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.R
import com.example.finamcetracker.models.BudgetEntry
import com.example.finamcetracker.models.BudgetHistory
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddBudgetEntryActivity : AppCompatActivity(){

    private lateinit var amount: EditText
    private lateinit var name: EditText
    private lateinit var date: EditText
    private lateinit var addEntryButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_budget_entry)
        getRefferences()
        initializeAddEntryButton()
    }


    fun getRefferences(){
        amount = findViewById(R.id.AmountValue)
        name = findViewById(R.id.NameValue)
        date = findViewById(R.id.editTextDate)
        addEntryButton = findViewById(R.id.AddEntryButton)
    }


    fun initializeAddEntryButton(){
        addEntryButton.setOnClickListener {
            val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
            val username = prefs.getString("username", "")

            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val localDate = LocalDate.parse(date.text, formatter)
            try {
                val budgetEntry = BudgetEntry(
                    amount.text.toString().toDouble(),
                    name.text.toString(),
                    Date(localDate.toEpochDay())
                )

                BudgetHistory.entries.add(budgetEntry)
                BudgetHistory.saveToFile(this, username!!)

            } catch (e: Exception) {
                Log.e("initializeAddEntryButton", "Error creating entry", e)
            }




        }
    }
}