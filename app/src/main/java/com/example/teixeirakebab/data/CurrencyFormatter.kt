package com.example.teixeirakebab.data

/**
 * Utility class for formatting currency values
 * Converts cents to Brazilian Real format (R$ X,XX)
 */
object CurrencyFormatter {
    
    /**
     * Format cents to Brazilian Real currency string
     * @param cents Amount in cents (e.g., 1850 for R$18,50)
     * @return Formatted currency string (e.g., "R$ 18,50")
     */
    fun format(cents: Int): String {
        val reais = cents / 100
        val centavos = cents % 100
        return "R$ $reais,$centavos".replace(",0", ",00")
    }
    
    /**
     * Format cents to Brazilian Real currency string with leading zero for centavos
     * @param cents Amount in cents (e.g., 1850 for R$18,50)
     * @return Formatted currency string (e.g., "R$ 18,50")
     */
    fun formatWithLeadingZero(cents: Int): String {
        val reais = cents / 100
        val centavos = cents % 100
        return "R$ $reais,${String.format("%02d", centavos)}"
    }
} 