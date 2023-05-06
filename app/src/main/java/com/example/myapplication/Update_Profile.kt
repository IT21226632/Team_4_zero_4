package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.myapplication.databinding.ActivityUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Update_Profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUpdateProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.updatepassword.setOnClickListener{
                val user = auth.currentUser
                val password = binding.Password.text.toString()
            if(checkPasswordfield()){

                    user?.updatePassword(password)?.addOnCompleteListener{
                        //if success
                        if(it.isSuccessful){
                            Toast.makeText(this, "update successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            Log.e("error: ", it.exception.toString())
                        }
                    }

            }
        }

        binding.updateemail.setOnClickListener{
            val user = auth.currentUser
            val email = binding.EmailAddress.text.toString()
            if(checkEmailfield()){
                user?.updateEmail(email)?.addOnCompleteListener{
                    //if success
                    if(it.isSuccessful){
                        Toast.makeText(this, "update successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }

    }

    private fun checkPasswordfield(): Boolean{
        if(binding.Password.text.toString() == ""){
            binding.Password.error = "This is required field"
            return false
        }
        if(binding.Password.length() <= 6){
            binding.Password.error = "password should have at least 6 characters"
            return false
        }
        return true
    }
    private fun checkEmailfield(): Boolean{
        val email = binding.EmailAddress.text.toString()
        if(binding.EmailAddress.text.toString()==""){
            binding.EmailAddress.error = "This is required field"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.EmailAddress.error = "check email format"
            return false
        }
        return true
    }
}