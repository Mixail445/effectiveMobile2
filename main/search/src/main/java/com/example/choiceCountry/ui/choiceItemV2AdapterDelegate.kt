package com.example.choiceCountry.ui

import com.example.common.BaseItem
import com.example.search.databinding.ItemTextChoiceFragmentV1Binding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun choiceItemV2AdapterDelegate() =
    adapterDelegateViewBinding<TitleTextForRcUi, BaseItem, ItemTextChoiceFragmentV1Binding>(
        viewBinding = { layoutInflater, parent ->
            ItemTextChoiceFragmentV1Binding.inflate(layoutInflater, parent, false)
        },
    ) {
        bind {
            binding.run {
                with(item) {
                    tvTitleV1.text = text
                }
            }
        }
    }
