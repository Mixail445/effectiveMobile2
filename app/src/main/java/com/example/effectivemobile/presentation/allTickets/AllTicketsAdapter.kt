package com.example.effectivemobile.presentation.allTickets

import com.example.effectivemobile.presentation.common.BaseItem
import com.example.utils.itemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AllTicketsAdapter() : AsyncListDifferDelegationAdapter<BaseItem>(diffUtils()) {
    init {
        delegatesManager
            .addDelegate(
                ALL_TICKETS_VIEW_TYPE,
                allTicketsAdapterDelegate(),
            )
    }

    companion object {
        const val ALL_TICKETS_VIEW_TYPE = -1001
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
