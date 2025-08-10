package com.example.teixeirakebab.data

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for CurrencyFormatter
 */
class CurrencyFormatterTest {
    
    @Test
    fun `format with zero cents returns R$ 0,00`() {
        val result = CurrencyFormatter.format(0)
        assertEquals("R$ 0,00", result)
    }
    
    @Test
    fun `format with 1000 cents returns R$ 10,00`() {
        val result = CurrencyFormatter.format(1000)
        assertEquals("R$ 10,00", result)
    }
    
    @Test
    fun `format with 1850 cents returns R$ 18,50`() {
        val result = CurrencyFormatter.format(1850)
        assertEquals("R$ 18,50", result)
    }
    
    @Test
    fun `format with 2500 cents returns R$ 25,00`() {
        val result = CurrencyFormatter.format(2500)
        assertEquals("R$ 25,00", result)
    }
    
    @Test
    fun `format with 4000 cents returns R$ 40,00`() {
        val result = CurrencyFormatter.format(4000)
        assertEquals("R$ 40,00", result)
    }
    
    @Test
    fun `formatWithLeadingZero with 50 cents returns R$ 0,50`() {
        val result = CurrencyFormatter.formatWithLeadingZero(50)
        assertEquals("R$ 0,50", result)
    }
    
    @Test
    fun `formatWithLeadingZero with 1500 cents returns R$ 15,00`() {
        val result = CurrencyFormatter.formatWithLeadingZero(1500)
        assertEquals("R$ 15,00", result)
    }
} 