package com.example.finamcetracker.CostumeViews

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log

import android.widget.Button
import android.widget.LinearLayout
import com.example.finamcetracker.Activities.ViewBudgetEntryActivity
import com.example.finamcetracker.models.BudgetEntry
import com.example.finamcetracker.models.BudgetHistory

class BudgetEntryView (context: Context, attrs: AttributeSet?, entry: BudgetEntry): LinearLayout(context, attrs){
    private val button = Button(context);
    private val deleteButton = Button(context);
    init{
        orientation=HORIZONTAL
        button.text = entry.toString()

        addView(button)
        addView(deleteButton)
        button.setOnClickListener {
            val intent = Intent(context, ViewBudgetEntryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("entry", entry)
            context.startActivity(intent)
        }
        deleteButton.setOnClickListener {
            //delete logic here

        }

    }
}