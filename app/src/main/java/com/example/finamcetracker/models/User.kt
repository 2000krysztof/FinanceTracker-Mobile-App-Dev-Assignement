package com.example.finamcetracker.models

import android.util.Log
import com.google.gson.Gson

data class User(
    val name : String,
    val password : String
) {

    fun toJson(){
        var gson = Gson()
        var output = gson.toJson(this)
        Log.d("output", output)
    }
}