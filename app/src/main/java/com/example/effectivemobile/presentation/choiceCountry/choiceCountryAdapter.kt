package com.example.effectivemobile.presentation.choiceCountry

import com.example.effectivemobile.presentation.common.BaseItem
import com.example.utils.itemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ChoiceCountryAdapter() : AsyncListDifferDelegationAdapter<BaseItem>(diffUtils()) {
    init {
        delegatesManager
            .addDelegate(
                CHOICE_COUNTRY_VIEW_TYPE,
                choiceItemAdapterDelegate(),
            )
            .addDelegate(
                CHOICE_TEXT_VIEW_TYPE_V1,
                choiceItemV2AdapterDelegate(),
            ).addDelegate(
                CHOICE_TEXT_VIEW_TYPE_V2,
                choiceItemV3AdapterDelegate(),
            )
    }

    companion object {
        const val CHOICE_COUNTRY_VIEW_TYPE = -1001
        const val CHOICE_TEXT_VIEW_TYPE_V1 = -1002
        const val CHOICE_TEXT_VIEW_TYPE_V2 = -1003
    }
}

private fun diffUtils() =
    com.example.utils.itemCallback<BaseItem>(
        areItemsTheSame = { oldItem, newItem -> oldItem.itemId == newItem.itemId },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        getChangePayload = { _, _ ->
            Any()
        },
    )
