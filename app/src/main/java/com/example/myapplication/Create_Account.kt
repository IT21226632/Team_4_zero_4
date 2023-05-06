package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Create_Account : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var binding: ActivityCreateAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.createBtn.setOnClickListener{
            val email = binding.EmailAddress2.text.toString()
            val password = binding.Password2.text.toString()
          if(checkAllField()){
              auth.createUserWithEmailAndPassword(email , password).addOnCompleteListener{
                  if(it.isSuccessful){
                      auth.signOut()
                      Toast.makeText(this, "Account created Successfully", Toast.LENGTH_SHORT).show()
                      val intent = Intent( this, Loging_Account::class.java)
                      startActivity(intent)
                      //to distroy activity
                      finish()
                  }
                  else{
                      Log.e("error: ", it.exception.toString())
                  }
              }
          }

        }

        val baccount : TextView = findViewById(R.id.businessaccount)

        //add login
        baccount.setOnClickListener{
            val intent = Intent( this, Create_Pharmacy::class.java)
            startActivity(intent)
        }
    }

    private fun checkAllField():Boolean{
        val email = binding.EmailAddress2.text.toString()
        if(binding.EmailAddress2.text.toString()==""){
            binding.EmailAddress2.error = "This is required field"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.EmailAddress2.error = "check email format"
            return false
        }
        if (email.endsWith("admin.com")) { // check if email ends with "admin.com"
            binding.EmailAddress2.error = "Cannot create account with admin email"
            return false
        }
        if(binding.Password2.text.toString() == ""){
            binding.Password2.error = "This is required field"
            return false
        }
        if(binding.Password2.length() < 5){
            binding.Password2.error = "password should have at least 6 characters"
            return false
        }
        if(binding.confirmpassword.text.toString() == ""){
            binding.confirmpassword.error = "This is required field"
            return false
        }
        if(binding.Password2.text.toString() != binding.confirmpassword.text.toString()){
            binding.confirmpassword.error = "Password do not match"
            return false
        }
        if(!binding.checkBox.isChecked){
            binding.checkBox.error = "This is required"
            return false
        }
        return true
    }
}