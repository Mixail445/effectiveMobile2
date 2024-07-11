
package com.example.main.ui.mainscreen
import com.example.common.BaseItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainScreenAdapter : AsyncListDifferDelegationAdapter<BaseItem>(diffUtils()) {
    init {
        delegatesManager
            .addDelegate(
                MAIN_ITEM_VIEW,
                mainItemAdapterDelegate(),
            )
    }

    companion object {
        const val MAIN_ITEM_VIEW = -1001
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
