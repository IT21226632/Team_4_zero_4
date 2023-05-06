package com.example.crudclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.crudclient.databinding.ActivityMainBinding // added import statement

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchId : String = binding.searchId.text.toString()
            if  (searchId.isNotEmpty()){
                readData(searchId)
            }else{
                Toast.makeText(this,"Please enter the id number",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(id: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(id).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("name").value
                val country = it.child("country").value
                val price = it.child("price").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchId.text.clear()
                binding.readName.text = name.toString()
                binding.readCountry.text = country.toString()
                binding.readPrice.text = price.toString()
            }else{
                Toast.makeText(this,"Phone number does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
