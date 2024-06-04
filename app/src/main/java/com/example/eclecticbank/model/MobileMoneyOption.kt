package com.example.eclecticbank.model

data class MobileMoneyOption(
    val mobileMoneyProviderName: String,
    val mobileMoneyproviderIcon: Int,
    var isSelected: Boolean = false
)