package com.example.effectivemobile.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.choiceCountry.ui.ChoiceCountryFragmentDirections
import com.example.core.R
import com.example.main.ui.mainscreen.MainFragmentDirections
import com.example.main.ui.mainscreen.dialog.DialogSearchDirections
import com.example.navigation.Router
import com.example.navigation.Screens
import com.google.android.material.navigation.NavigationBarView
import javax.inject.Inject

class RouterImpl
    @Inject
    constructor(
        private val controller: Int,
    ) : Router {
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
                is Screens.AllTickets -> moveToAllTickets(bundle)
                is Screens.ChoiceCountry -> moveToChoiceCountry(bundle)
                Screens.FilterScreen -> back()
                is Screens.DialogSearch -> openDialogSearch(bundle)
                Screens.HotelsScreen -> moveToHotelsScreen()
                Screens.LocationScreen -> moveToLocationScreen()
                Screens.ProfileScreen -> moveToProfileScreen()
                Screens.SubscribeScreen -> moveToSubscribeScreen()
            }
        }

        private fun moveToSubscribeScreen() {
            navController?.navigate(com.example.effectivemobile.R.id.subscribesFragment)
        }

        private fun moveToProfileScreen() {
            navController?.navigate(com.example.effectivemobile.R.id.profileFragment)
        }

        private fun moveToLocationScreen() {
            navController?.navigate(com.example.effectivemobile.R.id.locationFragment)
        }

        private fun moveToHotelsScreen() {
            navController?.navigate(com.example.effectivemobile.R.id.hotelsFragment2)
        }

        private fun moveToChoiceCountry(bundle: Bundle?) {
            bundle?.let {
                navController?.navigate(
                    DialogSearchDirections.actionDialogSearchToChoiceCountryFragment(
                        title = it.getString("title") ?: "",
                        bottomTitle = it.getString("bottomTitle") ?: "",
                    ),
                )
            }
        }

        private fun moveToAllTickets(bundle: Bundle?) {
            bundle?.let {
                navController?.navigate(
                    ChoiceCountryFragmentDirections.actionChoiceCountryFragmentToAllTicketsFragment(
                        it.getString("title") ?: "",
                        it.getString("bottomTitle") ?: "",
                    ),
                )
            }
        }

        private fun moveToMainScreen() {
            navController?.navigate(com.example.effectivemobile.R.id.mainFragment)
        }

        private fun openDialogSearch(bundle: Bundle?) {
            bundle?.let {
                navController?.navigate(
                    MainFragmentDirections.actionMainFragmentToDialogSearch(
                        it.getString("title") ?: "",
                    ),
                )
            }
        }

        override fun back() {
            navController?.popBackStack()
        }
    }
