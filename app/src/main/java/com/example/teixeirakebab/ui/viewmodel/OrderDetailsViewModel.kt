package com.example.teixeirakebab.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teixeirakebab.data.entity.OrderWithItems
import com.example.teixeirakebab.data.database.AppDatabase
import com.example.teixeirakebab.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the order details screen
 */
class OrderDetailsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val orderRepository = OrderRepository(AppDatabase.getDatabase(application).orderDao())
    
    private val _uiState = MutableStateFlow(OrderDetailsUiState())
    val uiState: StateFlow<OrderDetailsUiState> = _uiState
    
    fun loadOrder(orderId: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        
        viewModelScope.launch {
            try {
                val order = orderRepository.getOrder(orderId)
                _uiState.value = _uiState.value.copy(
                    order = order,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load order"
                )
            }
        }
    }
    
    fun deleteOrder(onSuccess: () -> Unit) {
        val orderId = _uiState.value.order?.order?.id ?: return
        
        viewModelScope.launch {
            try {
                orderRepository.deleteOrder(orderId)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Failed to delete order"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

/**
 * UI state for the order details screen
 */
data class OrderDetailsUiState(
    val order: OrderWithItems? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) 