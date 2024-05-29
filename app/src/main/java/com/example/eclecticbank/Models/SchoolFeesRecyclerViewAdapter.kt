package com.example.eclecticbank.Models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.databinding.SchoolFeesOptionBinding


class SchoolFeesRecyclerViewAdapter (private var items: List<SchoolFeesOption>, private val itemClicked: (SchoolFeesOption) -> Unit): RecyclerView.Adapter<SchoolFeesRecyclerViewAdapter.ItemViewHolder>() {




    inner class ItemViewHolder(private val binding: SchoolFeesOptionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SchoolFeesOption) {
            binding.leadingIcon.setImageResource(item.widgetIcon)
            binding.cardText.text = item.widgetTitle
            binding.root.setOnClickListener {
                itemClicked(item)
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


    fun filterList(filteredList: List<SchoolFeesOption>) {
        items = filteredList
        notifyDataSetChanged()
    }




}