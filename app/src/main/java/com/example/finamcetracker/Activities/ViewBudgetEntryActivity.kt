package com.example.finamcetracker.Activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.R
import com.example.finamcetracker.models.BudgetEntry

class ViewBudgetEntryActivity : AppCompatActivity(){
    private lateinit var amount: EditText
    private lateinit var name: EditText
    private lateinit var date: EditText
    private lateinit var confirmButton: Button
    private lateinit var discardButton: Button

    private lateinit var editables: Array<EditText>


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.view_budget_entry)
        initializeFields()
        populateFields()
        val backButton = findViewById<Button>(R.id.BackButton)
        backButton.setOnClickListener {
            finish()
        }
        confirmButton.visibility = View.INVISIBLE
        discardButton.visibility = View.INVISIBLE
        for (e in editables){
            e.addTextChangedListener(textWatcher)
        }


    }

    fun initializeFields(){
        amount = findViewById<EditText>(R.id.AmountValue)
        name = findViewById<EditText>(R.id.NameValue)
        date = findViewById<EditText>(R.id.editTextDate)
        confirmButton = findViewById<Button>(R.id.ConfirmChanges)
        discardButton = findViewById<Button>(R.id.DiscardChanges)
        editables = arrayOf(amount, name, date)
    }
    fun populateFields(){
        val entry = intent.getSerializableExtra<BudgetEntry>("entry", BudgetEntry::class.java)
        amount.setText(entry?.value.toString())
        name.setText(entry?.name)
        date.setText(entry?.date.toString())
    }


    var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            confirmButton.visibility = View.VISIBLE
            discardButton.visibility = View.VISIBLE
        }

        override fun afterTextChanged(s: Editable) {}
    }


}