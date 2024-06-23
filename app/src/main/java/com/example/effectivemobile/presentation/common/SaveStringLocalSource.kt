package com.example.effectivemobile.presentation.common

import com.example.effectivemobile.presentation.mainscreen.SaveDataInSharedPref


interface SaveStringLocalSource {
    fun getId(): SaveDataInSharedPref?
    fun setId(favoriteData: SaveDataInSharedPref)
    fun clearId()
}