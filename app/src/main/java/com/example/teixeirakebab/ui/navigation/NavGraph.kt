package com.example.teixeirakebab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teixeirakebab.ui.screen.NewOrderScreen
import com.example.teixeirakebab.ui.screen.OrderDetailsScreen
import com.example.teixeirakebab.ui.screen.OrdersListScreen

/**
 * Navigation graph for the kebab store app
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.OrdersList.route
    ) {
        composable(Screen.OrdersList.route) {
            OrdersListScreen(
                onNewOrder = { navController.navigate(Screen.NewOrder.route) },
                onOrderClick = { orderId -> 
                    navController.navigate(Screen.OrderDetails.createRoute(orderId))
                }
            )
        }
        
        composable(Screen.NewOrder.route) {
            NewOrderScreen(
                onOrderSaved = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.OrderDetails.route,
            arguments = Screen.OrderDetails.arguments
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getLong("orderId") ?: return@composable
            OrderDetailsScreen(
                orderId = orderId,
                onBack = { navController.popBackStack() }
            )
        }
    }
} 