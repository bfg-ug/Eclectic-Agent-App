package com.example.eclecticbank.view.additionalScreens

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
import com.example.eclecticbank.databinding.FragmentCardlessWithdrawalBinding
import com.example.eclecticbank.viewModel.InputValidationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardlessWithdrawal : Fragment() {

    private var _binding: FragmentCardlessWithdrawalBinding? = null
    private val binding get() = _binding!!

    private val inputViewModel: InputValidationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentCardlessWithdrawalBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Back arrow
        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_cardlessWithdrawal_to_homeDashboardFragment)
        }

        setupTextWatchers()
        observeValidationResults()
    }

    private fun setupTextWatchers() {
        binding.intiatorsphoneNumberTextField.addTextChangedListener(createTextWatcher {
            inputViewModel.validatePhoneNumber(it)
        })
        binding.recipientsphoneNumberTextField.addTextChangedListener(createTextWatcher {
            inputViewModel.validateRecipient(it)
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
        inputViewModel.phoneNumberValidation.observe(viewLifecycleOwner) { result ->
            binding.intiatorsphoneNumberContainer.error = if (result.isValid) null else result.message
        }

        inputViewModel.recipientValidation.observe(viewLifecycleOwner) { result ->
            binding.recipentsphoneNumberContainer.error = if (result.isValid) null else result.message
        }

        inputViewModel.isCardlessWithdrawalFormValid.observe(viewLifecycleOwner) { isValid ->
            binding.makeDepositButton.isEnabled = isValid
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}