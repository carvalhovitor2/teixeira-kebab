package com.example.teixeirakebab.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * Room entity representing an order in the database
 */
@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerName: String,
    val totalCents: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
) 