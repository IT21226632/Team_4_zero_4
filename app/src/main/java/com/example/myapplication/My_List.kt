package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMyListBinding
import com.example.myapplication.databinding.ActivityPharmacyHomepageBinding
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class My_List : AppCompatActivity() {


    private lateinit var binding: ActivityMyListBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var pharmacyRecyleview : RecyclerView
    private lateinit var phatmacyArrayList: ArrayList<PharmacyData>
    private lateinit var  searchView: SearchView
    private lateinit var searchlist: ArrayList<PharmacyData>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profile : ImageView = findViewById(R.id.pharmacy_profile)

        //add my_list
        profile.setOnClickListener{
            val intent = Intent( this, Profile::class.java)
            startActivity(intent)
        }

        pharmacyRecyleview = findViewById(R.id.pharmacyList)
        searchView = findViewById(R.id.searchView)
        pharmacyRecyleview.layoutManager = LinearLayoutManager(this)
        pharmacyRecyleview.setHasFixedSize(true)

        phatmacyArrayList = arrayListOf<PharmacyData>()
        searchlist = arrayListOf<PharmacyData>()
        getdata()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchlist.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    phatmacyArrayList.forEach{
                        if(it.name!!.toLowerCase(Locale.getDefault()).contains(searchText)){
                            searchlist.add(it)
                        }
                    }
                    pharmacyRecyleview.adapter!!.notifyDataSetChanged()
                }else{
                    searchlist.clear()
                    searchlist.addAll(phatmacyArrayList)
                    pharmacyRecyleview.adapter!!.notifyDataSetChanged()
                }
                return false
            }


        })

    }

    private fun getdata(){

        dbref = FirebaseDatabase.getInstance().getReference("Pharmacy Directory")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(pharmacySnapshot in snapshot.children){

                        val pharmacy = pharmacySnapshot.getValue(PharmacyData::class.java)
                        phatmacyArrayList.add(pharmacy!!)

                    }
                    searchlist.addAll(phatmacyArrayList)
                    pharmacyRecyleview.adapter = pharmacyProfileAdapter(searchlist)


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}
