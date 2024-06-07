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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eclecticbank.view.adapter.MobileMoneyRecyclerViewAdapter
import com.example.eclecticbank.model.MobileMoneyOption
import com.example.eclecticbank.R
import com.example.eclecticbank.databinding.FragmentMobileMoneyWithdrawalBinding
import com.example.eclecticbank.viewModel.InputValidationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MobileMoneyWithdrawal : Fragment() {
    private val inputValidationViewModel:InputValidationViewModel by viewModels()

    private var _binding: FragmentMobileMoneyWithdrawalBinding? = null
    private val binding get() = _binding!!
    private lateinit var mobileMoneyRecyclerViewAdapter: MobileMoneyRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMobileMoneyWithdrawalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // List of mobile money Providers
        val items = listOf(
            MobileMoneyOption("Mpesa", R.drawable.mpesa),
            MobileMoneyOption("Airtel Money", R.drawable.airtel),
            MobileMoneyOption("MTN", R.drawable.mask),
        )

        //Back Arrow
        binding.toolbar.setOnClickListener{
            findNavController().navigate(R.id.action_mobileMoneyWithdrawal_to_homeDashboardFragment)
        }

        // Mobile Money service provider adapter
        mobileMoneyRecyclerViewAdapter = MobileMoneyRecyclerViewAdapter(items)

        //recyclerview viewbinding
        binding.mobileMoneyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mobileMoneyRecyclerViewAdapter
        }

        binding.phoneNumberTextField.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.phoneNumberTextField.setHintTextColor(Color.GRAY)
            }else{
                binding.phoneNumberTextField.setHintTextColor(Color.WHITE)
            }
        }

        setupTextWatchers()
        observeValidationResults()
    }

    private fun setupTextWatchers() {
        binding.phoneNumberTextField.addTextChangedListener(createTextWatcher {
            inputValidationViewModel.validatePhoneNumber(it)
        })

        binding.amountTextField.addTextChangedListener(createTextWatcher {
            inputValidationViewModel.validateAmount(it)
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

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}