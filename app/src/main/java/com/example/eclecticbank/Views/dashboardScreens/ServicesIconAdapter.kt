package com.example.eclecticbank.Views.dashboardScreens
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.Views.AdditionalScreens.BottonsheetFragement
import com.example.eclecticbank.databinding.ServiceIconLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class ServiceIconAdapter(private val items: List<Service>) : RecyclerView.Adapter<ServiceIconAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(private val binding: ServiceIconLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Service) {
            binding.iconImageView.setImageResource(item.serviceIcon)
            binding.servicestextview.text = item.serviceName
            binding.root.setOnClickListener {
                if(item.serviceName == "Cash withdrawal" ){
                    val bottomSheet = BottonsheetFragement.newInstance()
                    bottomSheet.show((itemView.context as AppCompatActivity).supportFragmentManager, bottomSheet.tag)
                }else{
                    it.findNavController().navigate(item.destinationScreen)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ServiceIconLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = items.size




}
