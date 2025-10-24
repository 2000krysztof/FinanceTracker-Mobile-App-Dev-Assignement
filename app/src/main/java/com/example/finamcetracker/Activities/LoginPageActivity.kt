package com.example.finamcetracker.Activities

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finamcetracker.R
import com.example.finamcetracker.models.User

class LoginPageActivity : AppCompatActivity(){

    private lateinit var signUpButton: Button
    private lateinit var logInButton: Button
    private lateinit var nameField: EditText
    private lateinit var passwordField: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_page)
        getRefferences()
        intializeLogInButton()
        initializeSignUpButton()

    }

    fun getRefferences(){
        signUpButton = findViewById(R.id.SignUpButton)
        logInButton = findViewById(R.id.LogInButton)
        nameField = findViewById(R.id.NameInputField)
        passwordField = findViewById(R.id.PasswordInputField)
    }

    fun intializeLogInButton(){
        logInButton.setOnClickListener {
            val user = User.getUserByName(this, nameField.text.toString())
            if(user == null){
                //TODO display error if user dosnt exist
                return@setOnClickListener
            }
            if(user.password == passwordField.text.toString()){
                logIn(user)
                return@setOnClickListener
            }
            //TODO display error if password is wrong
        }
    }

    fun initializeSignUpButton(){
        signUpButton.setOnClickListener {
            val user = User(nameField.text.toString(), passwordField.text.toString())
            if(user.userExists(this)){
                //TODO display error if user already exists
            }
            user.save(this)
            logIn(user)
        }
    }

    fun logIn(user: User){
        val prefs = getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putString("user", user.name)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()

    }

}