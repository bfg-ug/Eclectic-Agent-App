package com.example.eclecticbank.Models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.databinding.SchoolFeesOptionBinding


class SchoolFeesRecyclerViewAdapter (private var items: List<Schools>): RecyclerView.Adapter<SchoolFeesRecyclerViewAdapter.ItemViewHolder>() {




    inner class ItemViewHolder(private val binding: SchoolFeesOptionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Schools) {
            binding.cardText.text = item.schoolName
            binding.root.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SchoolFeesOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = items.size


    fun filterList(filteredList: List<Schools>) {
        items = filteredList
        notifyDataSetChanged()
    }




}