package com.example.crudadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val referenceId = binding.referenceId.text.toString()
            val updateName = binding.updateName.text.toString()
            val updateCountry = binding.updateCountry.text.toString()
            val updatePrice = binding.updatePrice.text.toString()
            updateData(referenceId,updateName,updateCountry,updatePrice)
        }
    }

    private fun updateData(id: String, name: String, country: String, price: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "name" to name,
            "country" to country,
            "price" to price
        )
        database.child(id).updateChildren(user).addOnSuccessListener {
            binding.referenceId.text.clear()
            binding.updateName.text.clear()
            binding.updateCountry.text.clear()
            binding.updatePrice.text.clear()
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}

    }

