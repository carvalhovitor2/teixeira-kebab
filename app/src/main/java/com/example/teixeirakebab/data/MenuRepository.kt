package com.example.teixeirakebab.data

/**
 * Repository providing static menu data for the kebab store
 * All prices are stored in cents (e.g., 1500 = R$15,00)
 */
object MenuRepository {
    
    /**
     * Static menu items - add new items here to expand the menu
     * Prices must be between 1000 (R$10,00) and 4000 (R$40,00) cents
     */
    private val menuItems = listOf(
        // Kebabs
        MenuItem("kebab_small", "Kebab", MenuItem.Category.FOOD, MenuItem.Size.SMALL, 1500),
        MenuItem("kebab_big", "Kebab", MenuItem.Category.FOOD, MenuItem.Size.BIG, 2500),
        
        // Durums
        MenuItem("durum_small", "Durum", MenuItem.Category.FOOD, MenuItem.Size.SMALL, 2000),
        MenuItem("durum_big", "Durum", MenuItem.Category.FOOD, MenuItem.Size.BIG, 3000),
        
        // Drinks
        MenuItem("soda", "Soda", MenuItem.Category.DRINK, null, 1000),
        MenuItem("water", "Water", MenuItem.Category.DRINK, null, 1200),
        MenuItem("beer", "Beer", MenuItem.Category.DRINK, null, 1800)
    )
    
    /**
     * Get all menu items
     */
    fun getAllItems(): List<MenuItem> = menuItems
    
    /**
     * Get menu items by category
     */
    fun getItemsByCategory(category: MenuItem.Category): List<MenuItem> =
        menuItems.filter { it.category == category }
    
    /**
     * Get a specific menu item by ID
     */
    fun getItemById(id: String): MenuItem? =
        menuItems.find { it.id == id }
} 