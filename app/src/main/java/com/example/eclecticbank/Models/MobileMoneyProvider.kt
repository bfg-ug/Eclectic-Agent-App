package com.example.eclecticbank.Models

data class MobileMoneyProvider(
    val mobileMoneyProviderName: String,
    val mobileMoneyproviderIcon: Int,
    var isSelected: Boolean = false
)