package com.example.finamcetracker.models

import java.io.Serializable
import java.util.Date

data class  BudgetEntry(
    val value:Double,
    val name: String,
    val date: Date,
): Serializable{

    override fun toString(): String {
        return "$value $name"
    }
}