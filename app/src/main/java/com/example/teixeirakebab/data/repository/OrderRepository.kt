package com.example.teixeirakebab.data.repository

import com.example.teixeirakebab.data.MenuItem
import com.example.teixeirakebab.data.dao.OrderDao
import com.example.teixeirakebab.data.entity.OrderEntity
import com.example.teixeirakebab.data.entity.OrderItemEntity
import com.example.teixeirakebab.data.entity.OrderWithItems
import kotlinx.coroutines.flow.Flow

/**
 * Repository for order-related operations
 */
class OrderRepository(
    private val orderDao: OrderDao
) {
    
    /**
     * Get all orders with their items, ordered by creation date (newest first)
     */
    fun getAllOrders(): Flow<List<OrderWithItems>> = orderDao.getAllOrdersWithItems()
    
    /**
     * Get a specific order with its items
     */
    suspend fun getOrder(orderId: Long): OrderWithItems? = orderDao.getOrderWithItems(orderId)
    
    /**
     * Create a new order with items
     */
    suspend fun createOrder(customerName: String, items: List<CartItem>): Long {
        val totalCents = items.sumOf { it.menuItem.priceCents * it.quantity }
        
        val order = OrderEntity(
            customerName = customerName,
            totalCents = totalCents
        )
        
        val orderItems = items.map { cartItem ->
            OrderItemEntity(
                orderId = 0, // Will be set by the DAO
                name = cartItem.menuItem.name,
                category = cartItem.menuItem.category.name,
                size = cartItem.menuItem.size?.name,
                unitPriceCents = cartItem.menuItem.priceCents,
                quantity = cartItem.quantity,
                lineTotalCents = cartItem.menuItem.priceCents * cartItem.quantity
            )
        }
        
        return orderDao.insertOrderWithItems(order, orderItems)
    }
    
    /**
     * Delete an order
     */
    suspend fun deleteOrder(orderId: Long) = orderDao.deleteOrder(orderId)
}

/**
 * Represents an item in the shopping cart
 */
data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int
) 