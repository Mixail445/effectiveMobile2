package com.example.choiceCountry.ui

import com.example.common.BaseItem
import com.example.search.databinding.ItemTicketsV2Binding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun choiceItemAdapterDelegate() =
    adapterDelegateViewBinding<ChoiceCountryUi, BaseItem, ItemTicketsV2Binding>(
        viewBinding = { layoutInflater, parent ->
            ItemTicketsV2Binding.inflate(layoutInflater, parent, false)
        },
    ) {
        bind {
            binding.run {
                with(item) {
                    tvTitle.text = title
                    tvPrice.text = price
                    tvTime.text = timeRange
                    ivColorBall.setCardBackgroundColor(viewColor)
                }
            }
        }
    }
