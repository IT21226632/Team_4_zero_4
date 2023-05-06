package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.myapplication.databinding.ActivityForgotPasswordBinding

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set view binding
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.confirmbtn.setOnClickListener {
            val email = binding.EmailAddress.text.toString()
            if (checkEmail()) {
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //email is sent
                        Toast.makeText(this, "Email sent!", Toast.LENGTH_SHORT).show()
                        // start another activity
                        val intent = Intent(this, Loging_Account::class.java)
                        startActivity(intent)
                        //to distroy activity
                        finish()
                    }
                }
            }
        }
    }

    private fun checkEmail(): Boolean {
        val email = binding.EmailAddress.text.toString()
        if (binding.EmailAddress.text.toString() == "") {
            binding.EmailAddress.error = "This is required field"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.EmailAddress.error = "check email format"
            return false
        }
        return true
    }
}