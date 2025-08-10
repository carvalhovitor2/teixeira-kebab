package com.example.teixeirakebab.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teixeirakebab.data.database.AppDatabase
import com.example.teixeirakebab.data.repository.OrderRepository
import com.example.teixeirakebab.ui.model.OrderItemUiState
import com.example.teixeirakebab.ui.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * ViewModel for the orders list screen
 */
class OrdersListViewModel(application: Application) : AndroidViewModel(application) {
    
    private val orderRepository = OrderRepository(AppDatabase.getDatabase(application).orderDao())
    
    private val _uiState = MutableStateFlow(OrderUiState(isLoading = true))
    val uiState: StateFlow<OrderUiState> = _uiState
    
    init {
        loadOrders()
    }
    
    private fun loadOrders() {
        viewModelScope.launch {
            try {
                orderRepository.getAllOrders()
                    .map { ordersWithItems ->
                        ordersWithItems.map { OrderItemUiState.fromOrderWithItems(it) }
                    }
                    .map { orderItems ->
                        OrderUiState(orders = orderItems)
                    }
                    .catch { error ->
                        _uiState.value = OrderUiState(error = error.message ?: "Unknown error")
                    }
                    .collect { state ->
                        _uiState.value = state
                    }
            } catch (e: Exception) {
                _uiState.value = OrderUiState(error = e.message ?: "Unknown error")
            }
        }
    }
    
    fun deleteOrder(orderId: Long) {
        viewModelScope.launch {
            try {
                orderRepository.deleteOrder(orderId)
                // The Flow will automatically update the UI
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message ?: "Failed to delete order")
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
} 