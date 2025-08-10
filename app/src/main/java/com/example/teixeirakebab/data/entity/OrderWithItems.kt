package com.example.teixeirakebab.data.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing an order with all its items
 * Used for Room relationships
 */
data class OrderWithItems(
    @Embedded
    val order: OrderEntity,
    
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val items: List<OrderItemEntity>
) 