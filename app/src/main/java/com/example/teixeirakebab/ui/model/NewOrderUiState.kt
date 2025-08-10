package com.example.teixeirakebab.ui.model

import com.example.teixeirakebab.data.MenuItem
import com.example.teixeirakebab.data.repository.CartItem

/**
 * UI state for the new order screen
 */
data class NewOrderUiState(
    val customerName: String = "",
    val selectedCategory: MenuItem.Category = MenuItem.Category.FOOD,
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalCents: Int = cartItems.sumOf { it.menuItem.priceCents * it.quantity }
    val totalFormatted: String = formatCurrency(totalCents)
    val canSave: Boolean = customerName.isNotBlank() && cartItems.isNotEmpty()
    
    private fun formatCurrency(cents: Int): String {
        val reais = cents / 100
        val centavos = cents % 100
        return "R$ $reais,${String.format("%02d", centavos)}"
    }
}

/**
 * UI state for a cart item in the new order screen
 */
data class CartItemUiState(
    val menuItem: MenuItem,
    val quantity: Int,
    val lineTotalCents: Int,
    val lineTotalFormatted: String
) {
    companion object {
        fun fromCartItem(cartItem: CartItem): CartItemUiState {
            val lineTotalCents = cartItem.menuItem.priceCents * cartItem.quantity
            return CartItemUiState(
                menuItem = cartItem.menuItem,
                quantity = cartItem.quantity,
                lineTotalCents = lineTotalCents,
                lineTotalFormatted = formatCurrency(lineTotalCents)
            )
        }
        
        private fun formatCurrency(cents: Int): String {
            val reais = cents / 100
            val centavos = cents % 100
            return "R$ $reais,${String.format("%02d", centavos)}"
        }
    }
} 