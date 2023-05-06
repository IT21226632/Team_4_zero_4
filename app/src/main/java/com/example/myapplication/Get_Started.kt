package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Get_Started : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val login: Button = findViewById(R.id.login)
        val signup: Button = findViewById(R.id.get_signup)

        //add login
        login.setOnClickListener{
            val intent = Intent( this, Loging_Account::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener{
            val intent = Intent( this, Create_Account::class.java)
            startActivity(intent)
        }
    }
}