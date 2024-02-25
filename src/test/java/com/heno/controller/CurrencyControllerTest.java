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

class CurrencyControllerTest {

    // Mocks for dependencies
    @Mock
    private CurrencyService currencyServiceService;

    // Class under test
    @InjectMocks
    private CurrencyController currencyController;

    /**
     * Set up method to initialize mocks and test data before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /**
     * Test case for the allCurrencies method in the CurrencyController class.
     */
    @Test
    void testAllCurrencies() {
        // When
        String result = currencyController.allCurrenciesPage();

        // Then
        assertEquals("currencies", result);
    }
    /**
     * Test case for the getAllViewCurrencies method in the CurrencyController class.
     */
    @Test
    void testGetAllViewCurrencies() {
        // When
        ResponseEntity<?> response = currencyController.getAllViewCurrencies();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(currencyServiceService, times(1)).findAllView();
    }
    /**
     * Test case for the getAllCurrencies method in the CurrencyController class.
     */
    @Test
    void testGetAllCurrencies() {
        // When
        ResponseEntity<?> response = currencyController.getAllCurrencies();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(currencyServiceService, times(1)).findAll();
    }
    /**
     * Test case for the addCurrency method in the CurrencyController class.
     */
    @Test
    void testAddCurrency() {
        // When
        ResponseEntity<?> response = currencyController.addCurrency(1L);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(currencyServiceService, times(1)).addCurrency(1L);
    }

    /**
     * Test case for the deleteCurrency method in the CurrencyController class.
     */
    @Test
    void testDeleteCurrency() {
        // When
        ResponseEntity<?> response = currencyController.deleteCurrency(1L);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(currencyServiceService, times(1)).deleteCurrency(1L);
    }

}