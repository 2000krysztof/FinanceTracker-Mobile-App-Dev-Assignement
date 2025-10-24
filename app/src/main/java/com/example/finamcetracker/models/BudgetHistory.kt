package com.example.finamcetracker.models

import android.R
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class BudgetHistory {
    var entries: MutableList<BudgetEntry> = mutableListOf();
    constructor(){
        if(BudgetHistory.instance == null)
            BudgetHistory.instance = this
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
        val file = File(context.filesDir, name)
        if(!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
            return false
        }

        val gson = Gson()
        val content = file.readText()
        try{
            entries = gson.fromJson(content, object : TypeToken<MutableList<BudgetEntry>>() {}.type)
        }catch(e : Exception){
            entries = mutableListOf()
        }
        return true
    }


    fun saveToFile(context: Context, name: String){
        val file = File(context.filesDir, name)
        if(!file.exists()) {
            file.createNewFile()
        }
        val gson = Gson()
        val serialized = gson.toJson(entries)
        file.writeText(serialized)
    }

    companion object{
        var instance: BudgetHistory? = null
    }

}