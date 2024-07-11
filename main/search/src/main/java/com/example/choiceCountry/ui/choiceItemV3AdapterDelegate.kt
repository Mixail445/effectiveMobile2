package com.example.choiceCountry.ui

import com.example.common.BaseItem
import com.example.search.databinding.ItemTextChoiceFragmentV2Binding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun choiceItemV3AdapterDelegate() =
    adapterDelegateViewBinding<BottomTextForRcUi, BaseItem, ItemTextChoiceFragmentV2Binding>(
        viewBinding = { layoutInflater, parent ->
            ItemTextChoiceFragmentV2Binding.inflate(layoutInflater, parent, false)
        },
    ) {
        bind {
            binding.run {
                with(item) {
                    textView20.text = text
                }
            }
        }
    }
