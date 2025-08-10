package com.example.teixeirakebab.data.dao

import androidx.room.*
import com.example.teixeirakebab.data.entity.OrderEntity
import com.example.teixeirakebab.data.entity.OrderItemEntity
import com.example.teixeirakebab.data.entity.OrderWithItems
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for order-related database operations
 */
@Dao
interface OrderDao {
    
    /**
     * Get all orders with their items, ordered by creation date (newest first)
     */
    @Transaction
    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun getAllOrdersWithItems(): Flow<List<OrderWithItems>>
    
    /**
     * Get a specific order with its items by order ID
     */
    @Transaction
    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getOrderWithItems(orderId: Long): OrderWithItems?
    
    /**
     * Insert a new order and return its ID
     */
    @Insert
    suspend fun insertOrder(order: OrderEntity): Long
    
    /**
     * Insert order items
     */
    @Insert
    suspend fun insertOrderItems(items: List<OrderItemEntity>)
    
    /**
     * Delete an order by ID (cascade will delete related items)
     */
    @Query("DELETE FROM orders WHERE id = :orderId")
    suspend fun deleteOrder(orderId: Long)
    
    /**
     * Insert order and items in a transaction
     */
    @Transaction
    suspend fun insertOrderWithItems(order: OrderEntity, items: List<OrderItemEntity>): Long {
        val orderId = insertOrder(order)
        val itemsWithOrderId = items.map { it.copy(orderId = orderId) }
        insertOrderItems(itemsWithOrderId)
        return orderId
    }
} 