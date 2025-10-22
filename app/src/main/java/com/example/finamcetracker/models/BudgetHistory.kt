package com.example.finamcetracker.models

class BudgetHistory {
    val entries: MutableList<BudgetEntry> = mutableListOf();
    constructor(){

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


}