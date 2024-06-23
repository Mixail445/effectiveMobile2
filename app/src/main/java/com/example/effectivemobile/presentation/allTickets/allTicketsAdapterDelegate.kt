package com.example.effectivemobile.presentation.allTickets

import androidx.core.view.isVisible
import com.example.effectivemobile.databinding.ItemTicketBinding
import com.example.effectivemobile.presentation.common.BaseItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun allTicketsAdapterDelegate() = adapterDelegateViewBinding<AllTicketsUi, BaseItem, ItemTicketBinding>(
    viewBinding = { layoutInflater, parent ->
        ItemTicketBinding.inflate(layoutInflater, parent, false)
    },
) {
    bind {
        binding.run {
            with(item) {
                if (badge == null) { mcvOptional.isVisible = false } else { tvOptional.text = badge } // todo
                if (!hasTransfer) { tvOptionalTwo.isVisible = false } // todo
                tvPriceTicket.text = price
                tvTimeOtp.text = departure
                tvCodeOne.text = codeOne
                tvCodeTwo.text = codeTwo
                tvTimeTwo.text = arrival
                tvTimeAll.text = providerName
            }
        }
    }
}
