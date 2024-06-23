package com.example.effectivemobile.presentation.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.effectivemobile.R
import com.example.effectivemobile.presentation.bottomFragment.BottomFragmentDirections
import com.example.effectivemobile.presentation.choiceCountry.ChoiceCountryFragmentDirections
import com.example.effectivemobile.presentation.mainscreen.dialog.DialogSearchDirections
import com.google.android.material.navigation.NavigationBarView
import javax.inject.Inject

class RouterImpl
@Inject
constructor(private val controller: Int) : Router {
    private var navController: NavController? = null

    override fun init(
        fragment: Fragment,
        fragmentManager: FragmentManager,
        tabElementView: NavigationBarView?,
    ) {
        val navHostFragment = fragmentManager.findFragmentById(controller) as NavHostFragment?
        navController = navHostFragment?.navController ?: fragment.findNavController()
        navController?.let {
            if (tabElementView != null) {
                NavigationUI.setupWithNavController(tabElementView, it)
            }
        }
    }

    override fun clear() {
        navController = null
    }

    override fun navigateTo(
        screen: Screens,
        bundle: Bundle?,
    ) {
        when (screen) {
            Screens.MainScreen -> moveToMainScreen()
            is Screens.AllTickets -> moveToAllTickets(screen.title, screen.bottomTitle)
            is Screens.ChoiceCountry -> moveToChoiceCountry(screen.title, screen.bottomTitle)
            Screens.FilterScreen -> back()
            Screens.EmptyScreen -> moveToEmptyScreen()
            is Screens.DialogSearch -> openDialogSearch(screen.title)
        }
    }
    private fun moveToChoiceCountry(title: String, bottomTitle: String) {
        navController?.navigate(DialogSearchDirections.actionDialogSearchToChoiceCountryFragment(title = title, bottomTitle = bottomTitle))
    }
    private fun moveToAllTickets(title: String, bottomTitle: String) {
        navController?.navigate(ChoiceCountryFragmentDirections.actionChoiceCountryFragmentToAllTicketsFragment(title, bottomTitle))
    }
    private fun moveToEmptyScreen() {
        navController?.navigate(R.id.action_dialogSearch_to_emptyFragment)
    }
    private fun moveToMainScreen() {
        navController?.navigate(R.id.mainFragment)
    }
    private fun openDialogSearch(title: String) {
        navController?.navigate(BottomFragmentDirections.actionBottomFragmentToDialogSearch(title))
    }
    override fun back() {
        navController?.popBackStack()
    }
}
