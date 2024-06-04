package com.example.eclecticbank.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.databinding.SchoolFeesOptionBinding
import com.example.eclecticbank.model.Schools


class SchoolFeesRecyclerViewAdapter (private val clickListener: (Schools) -> Unit): RecyclerView.Adapter<SchoolFeesRecyclerViewAdapter.ItemViewHolder>() {

    private var schools = emptyList<Schools>()


    inner class ItemViewHolder(private val binding: SchoolFeesOptionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Schools) {
            binding.cardText.text = item.schoolName
            binding.root.setOnClickListener {
                clickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SchoolFeesOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = schools[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = schools.size


    fun filterList(filteredList: List<Schools>) {
        schools = filteredList
        notifyDataSetChanged()
    }


    internal fun setSchools(schools: List<Schools>) {
        this.schools = schools
        notifyDataSetChanged()
    }




}