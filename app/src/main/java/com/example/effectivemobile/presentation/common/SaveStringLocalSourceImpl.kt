package com.example.effectivemobile.presentation.common

import com.example.effectivemobile.presentation.mainscreen.SaveDataInSharedPref
import javax.inject.Inject

class SaveStringLocalSourceImpl @Inject
constructor(
    private val sharedPreferencesOne: SharedPreferencesOne<SaveDataInSharedPref>,
) : SaveStringLocalSource {
    override fun getId(): SaveDataInSharedPref? = sharedPreferencesOne.getData("key")

    override fun setId(stringToSharedPref: SaveDataInSharedPref) {
        sharedPreferencesOne.setData("key", stringToSharedPref)
    }

    override fun clearId() {
        sharedPreferencesOne.clearData()
    }
}
