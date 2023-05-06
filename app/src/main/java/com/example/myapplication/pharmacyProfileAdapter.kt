
package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class pharmacyProfileAdapter(private val pharmacyList : ArrayList<PharmacyData>) : RecyclerView.Adapter<pharmacyProfileAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.pharmacy_profile_res,
            parent, false
        )
        return myViewHolder(itemView)

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

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Name: TextView = itemView.findViewById(R.id.dname)
        val address: TextView = itemView.findViewById(R.id.dlocation)
        val time: TextView = itemView.findViewById(R.id.dtime)
        val price: TextView = itemView.findViewById(R.id.dprice)
        val quontity: TextView = itemView.findViewById(R.id.dquontity)
        val updateButton: Button = itemView.findViewById(R.id.update_btn)

        init {
            updateButton.setOnClickListener {
                val intent = Intent(itemView.context, updatePharmacy::class.java)
                intent.putExtra("name", Name.text.toString())
                intent.putExtra("location", address.text.toString())
                intent.putExtra("time", time.text.toString())
                intent.putExtra("price", price.text.toString())
                intent.putExtra("quantity", quontity.text.toString())
                itemView.context.startActivity(intent)
            }
        }


    }
}
