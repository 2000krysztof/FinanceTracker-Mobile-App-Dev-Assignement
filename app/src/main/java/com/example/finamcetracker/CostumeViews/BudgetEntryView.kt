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
import com.example.finamcetracker.R

class BudgetEntryView (context: Context, attrs: AttributeSet?, entry: BudgetEntry): LinearLayout(context, attrs){
    private val button = Button(context);
    private val deleteButton = Button(context);

    private var deleteCallbcak:()->Unit = {}
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
            BudgetHistory.entries.remove(entry)
            val prefs = this.context.getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
            val username = prefs.getString("username", "")
            BudgetHistory.saveToFile(this.context,username!!)
            deleteCallbcak()
        }
        deleteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.delete,0,0,0,)

    }

    fun setDeleteCallback(callback: ()->Unit = {}){
        deleteCallbcak = callback
    }
}