package com.heno.controller;

import com.heno.service.AgreementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * Set up method to initialize mocks and test data before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the allAgreements method in the AgreementController class.
     */
    @Test
    void testAllAgreements() {
        // When
        String result = agreementController.allAgreementsPage();

        // Then
        assertEquals("agreements", result);
    }
}