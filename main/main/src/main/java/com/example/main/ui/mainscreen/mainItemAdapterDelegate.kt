package com.example.main.ui.mainscreen

import com.example.common.BaseItem
import com.example.main.databinding.ItemMainBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun mainItemAdapterDelegate() =
    adapterDelegateViewBinding<OffersUi, BaseItem, ItemMainBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemMainBinding.inflate(layoutInflater, parent, false)
        },
    ) {
        bind {
            binding.run {
                with(item) {
                    tvTitle.text = title
                    tvTown.text = town
                    tvPrice.text = price
                    ivMain.setImageDrawable(photo)
                }
            }
        }
    }
