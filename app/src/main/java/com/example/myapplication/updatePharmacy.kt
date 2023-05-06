package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityUpdatePharmacyBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class updatePharmacy : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePharmacyBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePharmacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.update.setOnClickListener{
            val name = binding.name.text.toString()
            val location = binding.location.text.toString()
            val time = binding.time.text.toString()
            val price = binding.Price.text.toString()
            val quantity = binding.quantity.text.toString()

            updateData(name, location, time, price, quantity)
        }

        binding.deletephar.setOnClickListener{
            val name = binding.name.text.toString()
            if(name.isNotEmpty()){
                deleteData(name)
            }else{
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun updateData(name: String, location: String, time: String, price:String,quantity: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Pharmacy Directory")
        val pharmacy = mapOf<String, String>("location" to location,"time" to time, "price" to price, "quantity" to quantity )
        databaseReference.child(name).updateChildren(pharmacy).addOnSuccessListener {
            binding.name.text.clear()
            binding.location.text.clear()
            binding.time.text.clear()
            binding.Price.text.clear()
            binding.quantity.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            val intent = Intent( this, My_List::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener{
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(name: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Pharmacy Directory")
        databaseReference.child(name).removeValue().addOnSuccessListener {
            binding.name.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            val intent = Intent( this, Pharmacy_Homepage::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener{
            Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show()
        }
    }



}