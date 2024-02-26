package com.heno.controller;

import com.heno.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the CurrencyController class.
 */
class CurrencyControllerTest {

    // Mocks for dependencies
    @Mock
    private CurrencyService currencyService;

    // Class under test
    @InjectMocks
    private CurrencyController currencyController;

    /**
     * Set up method to initialize mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the allCurrenciesPage method in the CurrencyController class.
     * Tests the successful retrieval of the currencies page.
     */
    @Test
    void testAllCurrenciesPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = currencyController.allCurrenciesPage();

        // Then
        assertEquals("currencies", result);
    }

    /**
     * Test case for the getAllViewCurrencies method in the CurrencyController class.
     * Tests the successful retrieval of all view currencies.
     */
    @Test
    void testGetAllViewCurrencies_Success() {
        // Given
        when(currencyService.findAllView()).thenReturn(null);

        // When
        ResponseEntity<?> response = currencyController.getAllViewCurrencies();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(currencyService, times(1)).findAllView();
    }

    /**
     * Test case for the getAllViewCurrencies method in the CurrencyController class.
     * Tests an exception scenario when an error occurs while retrieving all view currencies.
     */
    @Test
    void testGetAllViewCurrencies_Exception() {
        // Given
        when(currencyService.findAllView()).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = currencyController.getAllViewCurrencies();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(currencyService, times(1)).findAllView();
    }

    /**
     * Test case for the getAllCurrencies method in the CurrencyController class.
     * Tests the successful retrieval of all currencies.
     */
    @Test
    void testGetAllCurrencies_Success() {
        // Given
        when(currencyService.findAll()).thenReturn(null);

        // When
        ResponseEntity<?> response = currencyController.getAllCurrencies();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(currencyService, times(1)).findAll();
    }

    /**
     * Test case for the getAllCurrencies method in the CurrencyController class.
     * Tests an exception scenario when an error occurs while retrieving all currencies.
     */
    @Test
    void testGetAllCurrencies_Exception() {
        // Given
        when(currencyService.findAll()).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = currencyController.getAllCurrencies();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(currencyService, times(1)).findAll();
    }

    /**
     * Test case for the addCurrency method in the CurrencyController class.
     * Tests the successful addition of a currency.
     */
    @Test
    void testAddCurrency_Success() {
        // Given
        doNothing().when(currencyService).addCurrency(anyLong());

        // When
        ResponseEntity<?> response = currencyController.addCurrency(123L);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(currencyService, times(1)).addCurrency(123L);
    }

    /**
     * Test case for the addCurrency method in the CurrencyController class.
     * Tests an exception scenario when an error occurs while adding a currency.
     */
    @Test
    void testAddCurrency_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(currencyService).addCurrency(anyLong());

        // When
        ResponseEntity<?> response = currencyController.addCurrency(123L);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(currencyService, times(1)).addCurrency(123L);
    }

    /**
     * Test case for the deleteCurrency method in the CurrencyController class.
     * Tests the successful deletion of a currency.
     */
    @Test
    void testDeleteCurrency_Success() {
        // Given
        doNothing().when(currencyService).deleteCurrency(anyLong());

        // When
        ResponseEntity<?> response = currencyController.deleteCurrency(456L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(currencyService, times(1)).deleteCurrency(456L);
    }

    /**
     * Test case for the deleteCurrency method in the CurrencyController class.
     * Tests an exception scenario when an error occurs while deleting a currency.
     */
    @Test
    void testDeleteCurrency_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(currencyService).deleteCurrency(anyLong());

        // When
        ResponseEntity<?> response = currencyController.deleteCurrency(456L);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(currencyService, times(1)).deleteCurrency(456L);
    }
}