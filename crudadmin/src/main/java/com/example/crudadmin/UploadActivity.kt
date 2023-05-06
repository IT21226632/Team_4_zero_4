package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val country = binding.uploadCountry.text.toString()
            val price = binding.uploadPrice.text.toString()
            val id = binding.uploadId.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Users")
            val users = UserData(name, country, price, id)
            database.child(id).setValue(users).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadCountry.text.clear()
                binding.uploadPrice.text.clear()
                binding.uploadId.text.clear()
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}