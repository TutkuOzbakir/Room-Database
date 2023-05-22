package com.tutkuozbakir.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutkuozbakir.room.databinding.RecyclerViewRowBinding
import com.tutkuozbakir.room.model.Place

class PlaceAdapter(val placeList: List<Place>): RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {

    class PlaceHolder(val binding: RecyclerViewRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return PlaceHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.binding.recyclerViewRowTextView.text = "Name: ${placeList.get(position).name}\nCountry: ${placeList.get(position).country}"
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}