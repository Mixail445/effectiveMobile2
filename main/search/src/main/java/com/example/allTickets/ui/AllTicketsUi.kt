package com.example.allTickets.ui

import android.os.Parcelable
import com.example.common.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllTicketsUi(
    val id: Long,
    val badge: String? = null,
    val price: String,
    val providerName: String,
    val company: String,
    val departure: String,
    val arrival: String,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val codeOne: String,
    val codeTwo: String,
    val isReturnable: Boolean,
    val isExchangable: Boolean,
    override val itemId: String,
) : BaseItem,
    Parcelable
