package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLogingAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Loging_Account : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLogingAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogingAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.loginbtn.setOnClickListener{
            val email = binding.EmailAddress.text.toString()
            val password = binding.Password.text.toString()
            if (email.contains("admin.com")) {
                val intent = Intent(this, AdminPage::class.java)
                startActivity(intent)
                finish()
            } else if (checkAllfield()) {
                auth.signInWithEmailAndPassword(email , password).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Pharmacy_Homepage::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Log.e("error: ", it.exception.toString())
                        Toast.makeText(this, "Authentication failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



        binding.forgetpassword.setOnClickListener{
            val intent = Intent( this, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun checkAllfield():Boolean{
        val email = binding.EmailAddress.text.toString()
        val password = binding.Password.text.toString()
        if(email.isEmpty()){
            binding.EmailAddress.error = "This is required field"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.EmailAddress.error = "check email format"
            return false
        }
        if(password.isEmpty()){
            binding.Password.error = "This is required field"
            return false
        }
        return true
    }
}
