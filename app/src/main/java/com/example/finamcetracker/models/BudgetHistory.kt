package com.example.finamcetracker.models

import android.R
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

object BudgetHistory {
    var entries: MutableList<BudgetEntry> = mutableListOf();

    fun isEmpty(): Boolean{
        return entries.isEmpty()
    }

    fun sortByValue(amount: Int) : Array<BudgetEntry>{
        return entries.sortedBy { it.value }.take(amount).toTypedArray();
    }

    fun sortByDate(amount: Int) : Array<BudgetEntry>{
        return entries.sortedBy { it.date }.take(amount).toTypedArray();
    }

    fun addEntry(entry : BudgetEntry) : Boolean{
        return entries.add(entry);
    }

    fun deleteEntry(entry : BudgetEntry) : Boolean{
        return entries.remove(entry);
    }

    fun loadFromFile(context: Context, name: String): Boolean{
        val file = File(context.filesDir, "$name.json")
        if(!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
            entries = mutableListOf()
            return false
        }

        val gson = GsonBuilder()
            .setDateFormat("MMM d, yyyy")
            .create()
        val content = file.readText()

        try{
            entries = gson.fromJson(content, object : TypeToken<MutableList<BudgetEntry>>() {}.type)
            return true
        }catch(e : Exception){
            entries = mutableListOf()
            return false
        }

    }


    fun saveToFile(context: Context, name: String){
        val file = File(context.filesDir, "$name.json")
        Log.d("inputStuff", "triggered")
        if(!file.exists()) {
            file.createNewFile()
        }
        val gson = Gson()
        val serialized = gson.toJson(entries)
        Log.d("inputStuff", serialized)
        file.writeText(serialized)
    }



}