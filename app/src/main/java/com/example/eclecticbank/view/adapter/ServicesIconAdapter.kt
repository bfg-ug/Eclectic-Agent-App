package com.example.eclecticbank.view.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eclecticbank.model.BottomSheetOption
import com.example.eclecticbank.model.Service
import com.example.eclecticbank.R
import com.example.eclecticbank.view.additionalScreens.BottonsheetFragement
import com.example.eclecticbank.databinding.ServiceIconLayoutBinding


class ServiceIconAdapter(private val items: List<Service>) : RecyclerView.Adapter<ServiceIconAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(private val binding: ServiceIconLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Service) {
            binding.iconImageView.setImageResource(item.serviceIcon)
            binding.servicestextview.text = item.serviceName
            binding.root.setOnClickListener {
                if(item.serviceName == "Cash withdrawal" ){


                    val cards = listOf(
                        BottomSheetOption(0,"Cardless Withdreawal", R.drawable.card_icon ),
                        BottomSheetOption(1,"Mobile Money Withdrawal", R.drawable.mobile_icon ),
                    )


                    val bottomSheet = BottonsheetFragement.newInstance(cards, "Select one cash withdrawal option")
                    bottomSheet.show((itemView.context as AppCompatActivity).supportFragmentManager, bottomSheet.tag)
                }else if(item.serviceName == "Cash Collection"){
                    val cards = listOf(
                        BottomSheetOption(0,"Direct collection", R.drawable.deposit_icon),
                        BottomSheetOption(1,"Indirect collections", R.drawable.payment_icon),
                        BottomSheetOption(2,"School fees", R.drawable.school_icon ),
                        BottomSheetOption(3,"Goverment collection", R.drawable.goverment_icon),
                    )


                    val bottomSheet = BottonsheetFragement.newInstance(cards, "Select one payment from the list")
                    bottomSheet.show((itemView.context as AppCompatActivity).supportFragmentManager, bottomSheet.tag)

                }

                else{
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
