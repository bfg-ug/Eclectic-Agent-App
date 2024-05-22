package com.example.eclecticbank.Models

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.R
import com.example.eclecticbank.Views.dashboardScreens.Service
import com.example.eclecticbank.Views.dashboardScreens.ServiceIconAdapter
import com.example.eclecticbank.databinding.MobileMoneyServiceItemCardBinding
import com.example.eclecticbank.databinding.ServiceIconLayoutBinding

class MobileMoneyAdapter(private val items: List<MobileMoneyProvider>):RecyclerView.Adapter<MobileMoneyAdapter.ItemViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    inner class ItemViewHolder(private val binding: MobileMoneyServiceItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        //Setting other radio buttons
        init {
            binding.serviceProviderToggle.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener

                // Uncheck the previous checked position if it's different
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    items[selectedPosition].isSelected = false
                    notifyItemChanged(selectedPosition)
                }

                // Set the new checked position
                items[adapterPosition].isSelected = true
                notifyItemChanged(adapterPosition)
                selectedPosition = adapterPosition
            }
        }

        fun bind(item: MobileMoneyProvider) {
            binding.serviceProviderLogoImagview.setImageResource(item.mobileMoneyproviderIcon)
            binding.serviceProviderTextview.text = item.mobileMoneyProviderName
            binding.serviceProviderToggle.isChecked = item.isSelected
            binding.cardview.setOnClickListener{
                binding.serviceProviderToggle.performClick()
            }


            // Change the background color if selected
            if (item.isSelected) {
                binding.cardview.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.blue))
                binding.serviceProviderTextview.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                binding.serviceProviderToggle.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.white))
            } else {
                binding.cardview.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
                binding.serviceProviderTextview.setTextColor(ContextCompat.getColor(binding.root.context, R.color.light_grey))
                binding.serviceProviderToggle.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(binding.root.context, R.color.light_grey))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileMoneyAdapter.ItemViewHolder {
        val binding = MobileMoneyServiceItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MobileMoneyAdapter.ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = items.size
}