package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val DELAY_TIME: Long = 5000 // 5 seconds in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Handler().postDelayed({
            // Start Activity2 after DELAY_TIME
            startActivity(Intent(this, Get_Started::class.java))
            finish() // Call finish to remove the Activity1 from the back stack
        }, DELAY_TIME)

    }
}
