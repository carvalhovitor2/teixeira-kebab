package com.example.teixeirakebab.ui.model

import com.example.teixeirakebab.data.entity.OrderWithItems
import java.time.format.DateTimeFormatter

/**
 * UI state for the orders list screen
 */
data class OrderUiState(
    val orders: List<OrderItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * UI state for a single order item in the list
 */
data class OrderItemUiState(
    val id: Long,
    val customerName: String,
    val formattedDate: String,
    val totalFormatted: String
) {
    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM HH:mm")
        
        fun fromOrderWithItems(orderWithItems: OrderWithItems): OrderItemUiState {
            return OrderItemUiState(
                id = orderWithItems.order.id,
                customerName = orderWithItems.order.customerName,
                formattedDate = orderWithItems.order.createdAt.format(dateFormatter),
                totalFormatted = formatCurrency(orderWithItems.order.totalCents)
            )
        }
        
        private fun formatCurrency(cents: Int): String {
            val reais = cents / 100
            val centavos = cents % 100
            return "R$ $reais,${String.format("%02d", centavos)}"
        }
    }
} 