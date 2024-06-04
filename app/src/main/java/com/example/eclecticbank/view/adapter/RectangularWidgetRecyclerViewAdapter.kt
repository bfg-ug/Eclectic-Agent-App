package com.example.eclecticbank.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.model.BottomSheetOption
import com.example.eclecticbank.databinding.RectangularIconWidgetBinding
import java.util.Locale


class RectangularWidgetRecyclerViewAdapter(private val items: List<BottomSheetOption>, private val itemClicked: (BottomSheetOption) -> Unit): RecyclerView.Adapter<RectangularWidgetRecyclerViewAdapter.ItemViewHolder>() {


    private var itemsFiltered: List<BottomSheetOption> = items


    inner class ItemViewHolder(private val binding: RectangularIconWidgetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BottomSheetOption) {
            binding.leadingIcon.setImageResource(item.widgetIcon)
            binding.cardText.text = item.widgetTitle
            binding.root.setOnClickListener {
                itemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RectangularIconWidgetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = items.size


    fun filter(query: String) {
        itemsFiltered = if (query.isEmpty()) {
            items
        } else {
            val filteredList = ArrayList<BottomSheetOption>()
            for (item in items) {
                if (item.widgetTitle.lowercase().contains(query.lowercase())) {
                    filteredList.add(item)
                }
            }
            filteredList
        }
        notifyDataSetChanged()
    }


}