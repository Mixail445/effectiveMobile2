package com.example.effectivemobile.presentation.choiceCountry

import com.example.effectivemobile.databinding.ItemTextChoiseFragmentV1Binding
import com.example.effectivemobile.presentation.common.BaseItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun choiceItemV2AdapterDelegate() = adapterDelegateViewBinding<TitleTextForRcUi, BaseItem, ItemTextChoiseFragmentV1Binding>(
    viewBinding = { layoutInflater, parent ->
        ItemTextChoiseFragmentV1Binding.inflate(layoutInflater, parent, false)
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
