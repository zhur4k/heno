package com.heno.controller;

import com.heno.service.AgreementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for the AgreementController class.
 */
class AgreementControllerTest {

    // Mocks for dependencies
    @Mock
    private AgreementService agreementService;

    // Class under test
    @InjectMocks
    private AgreementController agreementController;

    /**
     * Set up method to initialize mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the allAgreementsPage method in the AgreementController class.
     * Tests the successful retrieval of the agreements page.
     */
    @Test
    void testAllAgreementsPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = agreementController.allAgreementsPage();

        // Then
        assertEquals("agreements", result);
    }

    /**
     * Test case for the getAllAgreements method in the AgreementController class.
     * Tests the successful retrieval of all agreements.
     */
    @Test
    void testGetAllAgreements_Success() {
        // Given
        when(agreementService.findAll()).thenReturn(null);

        // When
        ResponseEntity<?> response = agreementController.getAllAgreements();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(agreementService, times(1)).findAll();
    }

    /**
     * Test case for the getAllAgreements method in the AgreementController class.
     * Tests an exception scenario when an error occurs while retrieving all agreements.
     */
    @Test
    void testGetAllAgreements_Exception() {
        // Given
        when(agreementService.findAll()).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = agreementController.getAllAgreements();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(agreementService, times(1)).findAll();
    }
}
