package com.example.eclecticbank.Views.dashboardScreens

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.util.Patterns
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.R
import com.example.eclecticbank.ViewModels.UserViewModel
import com.example.eclecticbank.ViewModels.onBoardingViewModel
import com.example.eclecticbank.databinding.FragmentCashDepositBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CashDepositFragment : Fragment() {

    private var _binding: FragmentCashDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashDepositBinding.inflate(inflater, container, false)

       viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        accountNumberFocusListener()
        phoneNumberFocusListener()
//        narrationFocusListener()


        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.homeDashboardFragment)
        }





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

                if (binding.accountNumberContainer.helperText == null && binding.phoneNumberContainer.helperText == null ){
                    var user = viewModel.fetchCustomerData()


                    //If all fields are filled do action
                    showCustomDialogue(binding.accountNumberTextField.text, binding.amountTextField.text, binding.narrationtextField.text)
                }else{
                    invalidInput()
                }







            }else{

                // If any field is not filled show Toast
                Toast.makeText(requireContext(), "All field must be filled", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun invalidInput() {
        var message = ""
        if (binding.accountNumberContainer.helperText != null){
            message += binding.accountNumberContainer.helperText
        }
        if (binding.phoneNumberContainer.helperText != null){
            message += binding.phoneNumberContainer.helperText
        }
        
        
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun accountNumberFocusListener() {
        binding.accountNumberTextField.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.accountNumberContainer.helperText= validAccountNumber()
            }

        }
    }

    private fun validAccountNumber(): String? {
        val accountnumber = binding.accountNumberTextField

        if (accountnumber.length() < 8){
            return "Invalid Account number"
        }
        return null
    }

    private fun phoneNumberFocusListener() {
        binding.phoneNumberContainer.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.phoneNumberContainer.helperText= validphoneNumber()
            }

        }
    }

    private fun validphoneNumber(): String? {
        val phonenumber = binding.phoneNumberTextField.text.toString()

        val pattern = Regex("^[+]?[0-9]{10,13}\$")
        return if (!pattern.matches(phonenumber)) {
            "Invalid Phone Number"
        } else {
            null
        }
    }

//    private fun narrationFocusListener() {
//        binding.narrationContainer.setOnFocusChangeListener{_, focused ->
//            if (!focused){
//                binding.phoneNumberContainer.helperText= validNarration()
//            }
//
//        }
//    }
//
//    private fun validNarration(): String? {
//        val narration = binding.narrationtextField.text
//
//        val regex = Regex("^[a-zA-Z ]+\$")
//
//            if (narration?.let { regex.matches(it) } == true){
//                return "Invalid narration"
//        }
//        return null
//    }





    private fun showCustomDialogue(accountNumberInput: Editable?, amountInput: Editable?, narrationInput: Editable?) {
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_alertdialogue_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnYes: Button = dialog.findViewById(R.id.confirm_button)
        val btnNo: Button = dialog.findViewById(com.google.android.material.R.id.cancel_button)
        val close: ImageView = dialog.findViewById(R.id.close_icon)

        val accountNumber: TextView = dialog.findViewById(R.id.accountnumber)
        accountNumber.text = accountNumberInput


        var amount: TextView = dialog.findViewById(R.id.amount)
        amount.text = amountInput


        var narration: TextView = dialog.findViewById(R.id.narrationfield)
        narration.text = narrationInput

        btnYes.setOnClickListener(){

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