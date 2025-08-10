package com.example.teixeirakebab.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teixeirakebab.data.MenuItem
import com.example.teixeirakebab.data.MenuRepository
import com.example.teixeirakebab.data.database.AppDatabase
import com.example.teixeirakebab.data.repository.CartItem
import com.example.teixeirakebab.data.repository.OrderRepository
import com.example.teixeirakebab.ui.model.NewOrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the new order screen
 */
class NewOrderViewModel(application: Application) : AndroidViewModel(application) {
    
    private val orderRepository = OrderRepository(AppDatabase.getDatabase(application).orderDao())
    
    private val _uiState = MutableStateFlow(NewOrderUiState())
    val uiState: StateFlow<NewOrderUiState> = _uiState
    
    fun updateCustomerName(name: String) {
        _uiState.update { it.copy(customerName = name) }
    }
    
    fun selectCategory(category: MenuItem.Category) {
        _uiState.update { it.copy(selectedCategory = category) }
    }
    
    fun addItemToCart(menuItem: MenuItem, quantity: Int) {
        val currentItems = _uiState.value.cartItems.toMutableList()
        
        // Check if item already exists in cart
        val existingIndex = currentItems.indexOfFirst { 
            it.menuItem.id == menuItem.id 
        }
        
        if (existingIndex >= 0) {
            // Update existing item quantity
            val existingItem = currentItems[existingIndex]
            currentItems[existingIndex] = existingItem.copy(
                quantity = existingItem.quantity + quantity
            )
        } else {
            // Add new item
            currentItems.add(CartItem(menuItem, quantity))
        }
        
        _uiState.update { it.copy(cartItems = currentItems) }
    }
    
    fun removeItemFromCart(menuItem: MenuItem) {
        val currentItems = _uiState.value.cartItems.toMutableList()
        currentItems.removeAll { it.menuItem.id == menuItem.id }
        _uiState.update { it.copy(cartItems = currentItems) }
    }
    
    fun updateItemQuantity(menuItem: MenuItem, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeItemFromCart(menuItem)
            return
        }
        
        val currentItems = _uiState.value.cartItems.toMutableList()
        val existingIndex = currentItems.indexOfFirst { it.menuItem.id == menuItem.id }
        
        if (existingIndex >= 0) {
            currentItems[existingIndex] = currentItems[existingIndex].copy(quantity = newQuantity)
            _uiState.update { it.copy(cartItems = currentItems) }
        }
    }
    
    fun saveOrder(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        
        if (!currentState.canSave) return
        
        _uiState.update { it.copy(isLoading = true) }
        
        viewModelScope.launch {
            try {
                orderRepository.createOrder(currentState.customerName, currentState.cartItems)
                onSuccess()
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to save order"
                    )
                }
            }
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
    
    fun getMenuItemsByCategory(category: MenuItem.Category): List<MenuItem> {
        return MenuRepository.getItemsByCategory(category)
    }
} 