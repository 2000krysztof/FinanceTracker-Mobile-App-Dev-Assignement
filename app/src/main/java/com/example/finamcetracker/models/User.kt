package com.example.finamcetracker.models

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

data class User(
    val name : String,
    val password : String
) {

    fun toJson() : String{
        val gson = Gson()
        return gson.toJson(this)
    }

    fun save(context: Context) : Boolean{
        if(userExists(context)) return false
        val file = File(context.filesDir, "users.json")
        if(!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
        val content = file.readText()
        val gson = Gson()
        val users = User.userJsonToList(context)
        users.add(this)
        file.writeText(gson.toJson(users))
        return true


    }


    fun userExists(context: Context) : Boolean{
        val file = File(context.filesDir, "users.json")
        if(!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
        val users = userJsonToList(context)
        for (user in users) {
            if(user.name == this.name){
                return true
            }
        }
        return false
    }


    companion object{
        fun getUserByName(context: Context, name : String) : User?{
            val file = File(context.filesDir, "users.json")
            if(!file.exists()) {
                file.createNewFile()
                file.writeText("[]")
            }
            val users = User.userJsonToList(context)
            for (user in users) {
                if(user.name == name){
                    return user
                }
            }
            return null
        }

        fun userJsonToList(context: Context): MutableList<User>{
            val file = File(context.filesDir, "users.json")
            val content = file.readText()
            val gson = Gson()
            try{
                return gson.fromJson(content, object : TypeToken<MutableList<User>>() {}.type)
            }catch(e : Exception){
                return mutableListOf()
            }


        }

        //Debug function for testing
        fun logUsers(context: Context){
            val file = File(context.filesDir, "users.json")
            if(!file.exists()) {
                return
            }
            Log.d("users", file.readText())
        }

        //Debug function for testing
        fun wipeAllUsers(context: Context){
            val file = File(context.filesDir, "users.json")
            if(!file.exists()) {
                return
            }
            file.writeText("[]");
        }
    }

}