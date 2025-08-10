package com.example.teixeirakebab.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Room entity representing an item within an order
 */
@Entity(
    tableName = "order_items",
    foreignKeys = [
        ForeignKey(
            entity = OrderEntity::class,
            parentColumns = ["id"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderId: Long,
    val name: String,
    val category: String, // "FOOD" or "DRINK"
    val size: String?, // "BIG", "SMALL", or null for drinks
    val unitPriceCents: Int,
    val quantity: Int,
    val lineTotalCents: Int
) 