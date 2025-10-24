package com.example.finamcetracker.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.R
import com.example.finamcetracker.models.BudgetEntry
import java.sql.Date

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

    }


    fun getRefferences(){
        amount = findViewById(R.id.AmountValue)
        name = findViewById(R.id.NameValue)
        date = findViewById(R.id.editTextDate)
        addEntryButton = findViewById(R.id.AddEntryButton)
    }


    fun initializeAddEntryButton(){
        addEntryButton.setOnClickListener {
            val budgetEntry = BudgetEntry(
                amount.text.toString().toDouble(),
                name.text.toString(),
                Date.valueOf(date.toString()))


        }
    }
}