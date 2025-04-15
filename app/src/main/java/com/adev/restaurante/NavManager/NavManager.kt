package com.adev.restaurante.NavManager

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adev.restaurante.view.CartView
import com.adev.restaurante.view.LoginView
import com.adev.restaurante.view.MenuView
import com.adev.restaurante.viewmodel.CartViewModel


@Composable
fun NavManager(){
    val navController= rememberNavController()

    val viewModel: CartViewModel = viewModel()

    NavHost(navController, startDestination = "MenuView") {
        composable("LoginView") {
            LoginView(navController)
        }

        composable("MenuView") {
            MenuView(viewModel,navController)
        }

        composable("CartView") {

            //pasar viewmodel
            CartView(viewModel, navController)
        }

    }
}