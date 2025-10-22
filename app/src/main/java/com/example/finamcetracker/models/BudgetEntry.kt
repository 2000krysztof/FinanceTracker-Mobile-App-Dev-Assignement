package com.example.finamcetracker.models

import java.util.Date

data class  BudgetEntry (
    val value:Double,
    val name: String,
    val date: Date,
){

    override fun toString(): String {
        return "$value $name"
    }
}