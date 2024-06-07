package com.example.eclecticbank.view.additionalScreens

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentSchoolFeesPaymentBinding
import com.example.eclecticbank.viewModel.InputValidationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolFeesPaymentFragment : Fragment() {
    private val inputValidationViewModel: InputValidationViewModel by viewModels()

    private var _binding: FragmentSchoolFeesPaymentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        // Inflate the layout for this fragment
        _binding = FragmentSchoolFeesPaymentBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val schoolName = arguments?.getString("schoolName")
        val schoolType = arguments?.getString("schoolType")
        val schoolLocation = arguments?.getString("schoolLocation")

        binding.schoolTitle.text = schoolName

        binding.admissionNumberTextField.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.admissionNumberTextField.setHintTextColor(Color.GRAY)
            }else{
                binding.admissionNumberTextField.setHintTextColor(Color.WHITE)
            }
        }

        binding.phoneNumberTextField.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                binding.phoneNumberTextField.setHintTextColor(Color.GRAY)
            }else{
                binding.phoneNumberTextField.setHintTextColor(Color.WHITE)
            }
        }

        setupTextWatchers()
        observeValidationResults()

        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_school_Fees_Payment_Fragment_to_school_fees_Fragment)
        }
    }

    private fun setupTextWatchers() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}