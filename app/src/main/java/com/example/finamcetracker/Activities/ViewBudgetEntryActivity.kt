package com.example.finamcetracker.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.R
import com.example.finamcetracker.models.BudgetEntry

class ViewBudgetEntryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.view_budget_entry)
        populateFields()
        val backButton = findViewById<Button>(R.id.BackButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    fun populateFields(){
        val entry = intent.getSerializableExtra<BudgetEntry>("entry", BudgetEntry::class.java)
        val amount = findViewById<TextView>(R.id.AmountValue)
        val name = findViewById<TextView>(R.id.NameValue)
        val date = findViewById<TextView>(R.id.editTextDate)

        amount.text = entry?.value.toString()
        name.text = entry?.name
        date.text = entry?.date.toString()
    }
}