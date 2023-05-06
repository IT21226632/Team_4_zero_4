package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.logout.setOnClickListener{
            auth.signOut()
            // start another activity
            val intent = Intent( this, Loging_Account::class.java)
            startActivity(intent)
            //to distroy activity
            finish()
        }

        //delete user
        binding.delete.setOnClickListener{
            val user = Firebase.auth.currentUser
            user?.delete()?.addOnCompleteListener{
                if(it.isSuccessful){
                    //account delete
                    Toast.makeText(this, "Account successfully deleted", Toast.LENGTH_SHORT).show()
                    // start another activity
                    val intent = Intent( this, Loging_Account::class.java)
                    startActivity(intent)
                }else{
                    Log.e("error: ", it.exception.toString())
                }
            }
        }

        val profile : TextView = findViewById(R.id.account)

        //add my_list
        profile.setOnClickListener{
            val intent = Intent( this, Update_Profile::class.java)
            startActivity(intent)
        }

    }
}