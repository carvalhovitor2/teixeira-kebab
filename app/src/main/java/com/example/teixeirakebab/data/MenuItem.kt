package com.example.teixeirakebab.data

/**
 * Represents a menu item in the kebab store
 * @param id Unique identifier for the menu item
 * @param name Display name of the item
 * @param category Whether it's food or drink
 * @param size Size option (Big/Small) - null for drinks
 * @param priceCents Price in cents (e.g., 1500 for R$15,00)
 */
data class MenuItem(
    val id: String,
    val name: String,
    val category: Category,
    val size: Size? = null,
    val priceCents: Int
) {
    enum class Category {
        FOOD, DRINK
    }
    
    enum class Size {
        BIG, SMALL
    }
    
    val displayName: String
        get() = when {
            size != null -> "$name ${size.name.lowercase().capitalize()}"
            else -> name
        }
}

private fun String.capitalize(): String {
    return if (isNotEmpty()) {
        this[0].uppercase() + substring(1)
    } else {
        this
    }
} 