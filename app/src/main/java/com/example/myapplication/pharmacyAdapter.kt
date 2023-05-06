package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class pharmacyAdapter(private val pharmacyList : ArrayList<PharmacyData>) : RecyclerView.Adapter<pharmacyAdapter.myViewHolder>(){


    private lateinit var mListner : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListner(listner: onItemClickListner){
        mListner = listner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pharmacy_res,
        parent, false)
        return myViewHolder(itemView,mListner)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentitem = pharmacyList[position]

        holder.Name.text = currentitem.name
        holder.address.text = currentitem.location
        holder.time.text = currentitem.time
        holder.price.text = currentitem.price
        holder.quontity.text = currentitem.quantity


    }

    override fun getItemCount(): Int {
        return pharmacyList.size
    }

    class myViewHolder(itemView : View, listner: onItemClickListner) : RecyclerView.ViewHolder(itemView){

        val Name : TextView = itemView.findViewById(R.id.dname)
        val address : TextView = itemView.findViewById(R.id.dlocation)
        val time : TextView = itemView.findViewById(R.id.dtime)
        val price : TextView = itemView.findViewById(R.id.dprice)
        val quontity : TextView = itemView.findViewById(R.id.dquontity)

        init {

            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }

        }

    }

}