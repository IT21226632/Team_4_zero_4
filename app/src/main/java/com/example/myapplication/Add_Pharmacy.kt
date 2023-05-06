package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityAddPharmacyBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add_Pharmacy : AppCompatActivity() {

    private lateinit var binding: ActivityAddPharmacyBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPharmacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Addbtn.setOnClickListener{
            val name = binding.name.text.toString()
            val location = binding.location.text.toString()
            val Time = binding.Time.text.toString()
            val price = binding.Price.text.toString()
            val Quantity = binding.Quantity.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Pharmacy Directory")//table name
            val pharmercies = PharmacyData(  name , location , Time , price , Quantity)

            // Use push() to generate a new child location with a unique key
            databaseReference.child(name).setValue(pharmercies).addOnSuccessListener {
                binding.name.text.clear()
                binding.location.text.clear()
                binding.Time.text.clear()
                binding.Price.text.clear()
                binding.Quantity.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent( this, Pharmacy_Homepage::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
