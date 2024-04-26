package com.example.eclecticbank.Views.dashboardScreens

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.R
import com.example.eclecticbank.ViewModels.UserViewModel
import com.example.eclecticbank.databinding.FragmentCashDepositBinding



class CashDepositFragment : Fragment() {

    private var _binding: FragmentCashDepositBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashDepositBinding.inflate(inflater, container, false)



        //Array list of bank option
        val languages = resources.getStringArray(R.array.banks)

        //dropdown adapter
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownitem,languages)

        binding.autoCompleteTextview.setAdapter(arrayAdapter)


        // make deposit button
        binding.makeDepositButton.setOnClickListener(){


            //Check if all fields have been filled
            if ((binding.accountNumberTextField.text?.isEmpty()
                    ?: binding.amountTextField.text?.isEmpty()
                    ?: binding.narrationtextField.text?.isEmpty()) != true
            ){

                //If all fields are filled do action
                showCustomDialogue(binding.amountTextField.text, binding.amountTextField.text, binding.narrationtextField.text)
            }else{

                // If any field is not filled show Toast
                Toast.makeText(requireContext(), "All field must be filled", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }



    private fun showCustomDialogue(accountNumberInput: Editable?, amountInput: Editable?, narrationInput: Editable?) {
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_alertdialogue_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnyes: Button = dialog.findViewById(R.id.confirm_button)
        val btnNo: Button = dialog.findViewById(com.google.android.material.R.id.cancel_button)
        val close: ImageView = dialog.findViewById(R.id.close_icon)

        val accountNumber: TextView = dialog.findViewById(R.id.accountnumber)
        accountNumber.text = accountNumberInput


        var amount: TextView = dialog.findViewById(R.id.amount)
        amount.text = amountInput


//        var narration: TextView = dialog.findViewById(R.id.narration)
//        narration.text = narrationInput

        btnyes.setOnClickListener(){
            dialog.dismiss()
            findNavController().navigate(R.id.action_cashDepositFragment_to_homeDashboardFragment)
        }



        close.setOnClickListener(){
            dialog.dismiss()
        }

        btnNo.setOnClickListener(){
            dialog.dismiss()
        }


        dialog.show()
    }

}