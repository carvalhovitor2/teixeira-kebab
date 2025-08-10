package com.example.teixeirakebab.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Sealed class defining all navigation screens in the app
 */
sealed class Screen(val route: String) {
    object OrdersList : Screen("orders_list")
    object NewOrder : Screen("new_order")
    object OrderDetails : Screen("order_details/{orderId}") {
        val arguments = listOf(
            navArgument("orderId") { type = NavType.LongType }
        )
        
        fun createRoute(orderId: Long) = "order_details/$orderId"
    }
} 