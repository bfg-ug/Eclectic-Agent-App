package com.example.eclecticbank.view.additionalScreens

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.eclecticbank.viewModel.UserViewModel
import com.example.eclecticbank.databinding.FragmentCashDepositBinding
import com.example.eclecticbank.viewModel.InputValidationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashDepositFragment : Fragment() {

    private val inputValidationViewModel: InputValidationViewModel by viewModels()

    private var _binding: FragmentCashDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashDepositBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)




        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_cashDepositFragment_to_homeDashboardFragment)
        }


        //Array list of bank option
        val languages = resources.getStringArray(R.array.banks)

        //dropdown adapter
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownitem,languages)

        binding.autoCompleteTextview.setAdapter(arrayAdapter)

        setupTextWatchers()
        observeValidationResults()


        // Make deposit button
        binding.makeDepositButton.setOnClickListener {
            if (inputValidationViewModel.isDepositFormValid.value == true) {
                performSubmitAction()
            } else {
                showToast("Please correct the errors before submitting.")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.autoCompleteTextview.setAdapter(null)
        _binding = null
    }


    private fun setupTextWatchers() {
        binding.accountNumberTextField.addTextChangedListener(createTextWatcher {
            inputValidationViewModel.validateAccountnumber(it)
        })
        binding.phoneNumberTextField.addTextChangedListener(createTextWatcher {
            inputValidationViewModel.validatePhoneNumber(it)
        })
        binding.amountTextField.addTextChangedListener(createTextWatcher {
            inputValidationViewModel.validateAmount(it)
        })
        binding.narrationtextField.addTextChangedListener(createTextWatcher {
            inputValidationViewModel.validateNarration(it)
        })
    }

    private fun createTextWatcher(validationFunction: (String) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validationFunction(s.toString())
            }
        }
    }

    private fun observeValidationResults() {
        inputValidationViewModel.accountNumberValidation.observe(viewLifecycleOwner) { result ->
            binding.accountNumberContainer.error = if (result.isValid) null else result.message
        }

        inputValidationViewModel.phoneNumberValidation.observe(viewLifecycleOwner) { result ->
            binding.phoneNumberContainer.error = if (result.isValid) null else result.message
        }

        inputValidationViewModel.amountValidation.observe(viewLifecycleOwner) { result ->
            binding.amountContainer.error = if (result.isValid) null else result.message
        }

        inputValidationViewModel.narrationValidation.observe(viewLifecycleOwner) { result ->
            binding.narrationContainer.error = if (result.isValid) null else result.message
        }

        inputValidationViewModel.isDepositFormValid.observe(viewLifecycleOwner) { isValid ->
            binding.makeDepositButton.isEnabled = isValid
        }
    }

    private fun performSubmitAction() {
        val accountNumberInput = binding.accountNumberTextField.text
        val amountInput = binding.amountTextField.text
        val narrationInput = binding.narrationtextField.text

        showCustomDialogue(accountNumberInput, amountInput, narrationInput)
    }






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

        btnYes.setOnClickListener{
            dialog.dismiss()
            findNavController().navigate(R.id.action_cashDepositFragment_to_homeDashboardFragment)

        }



        close.setOnClickListener{
            dialog.dismiss()
        }

        btnNo.setOnClickListener(){
            dialog.dismiss()
        }


        dialog.show()
    }

}