package com.example.effectivemobile.presentation.mainscreen

import com.example.effectivemobile.databinding.ItemMainBinding
import com.example.effectivemobile.presentation.common.BaseItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun mainItemAdapterDelegate() = adapterDelegateViewBinding<OffersUi, BaseItem, ItemMainBinding>(
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
