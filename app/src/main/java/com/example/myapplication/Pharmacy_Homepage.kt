package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityPharmacyHomepageBinding
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class Pharmacy_Homepage : AppCompatActivity() {

    private lateinit var binding: ActivityPharmacyHomepageBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var pharmacyRecyleview : RecyclerView
    private lateinit var phatmacyArrayList: ArrayList<PharmacyData>
    private lateinit var  searchView: SearchView
    private lateinit var searchlist: ArrayList<PharmacyData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPharmacyHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //add pharmacy
        binding.addPharmacy.setOnClickListener{
            val intent = Intent( this, Add_Pharmacy::class.java)
            startActivity(intent)
        }

        val login: ImageView = findViewById(R.id.profile_image)

        //add my_list
        login.setOnClickListener{
            val intent = Intent( this, My_List::class.java)
            startActivity(intent)
            //destroy activity
            finish()
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

                    var adapter = pharmacyAdapter(searchlist)
                    searchlist.addAll(phatmacyArrayList)
                    pharmacyRecyleview.adapter = adapter

                    adapter.setOnItemClickListner(object : pharmacyAdapter.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            Toast.makeText(this@Pharmacy_Homepage, "test $position",Toast.LENGTH_SHORT).show()
                            val intent = Intent( this@Pharmacy_Homepage, Location::class.java)
                            startActivity(intent)
                            //to distroy activity
                            finish()
                        }

                    })


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}