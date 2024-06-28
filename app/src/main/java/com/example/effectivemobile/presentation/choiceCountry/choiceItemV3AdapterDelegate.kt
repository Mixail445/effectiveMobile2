package com.example.effectivemobile.presentation.choiceCountry

import com.example.effectivemobile.databinding.ItemTextChoiceFragmentV2Binding
import com.example.effectivemobile.presentation.common.BaseItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun choiceItemV3AdapterDelegate() = adapterDelegateViewBinding<BottomTextForRcUi, BaseItem, ItemTextChoiceFragmentV2Binding>(
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
