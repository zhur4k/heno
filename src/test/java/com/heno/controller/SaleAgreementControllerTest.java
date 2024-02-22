package com.heno.controller;

import com.heno.dto.SaleAgreementAddDto;
import com.heno.dto.SaleAgreementEditDto;
import com.heno.model.*;
import com.heno.service.SaleAgreementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SaleAgreementController class.
 */
class SaleAgreementControllerTest {

    // Mocks for dependencies
    @Mock
    private SaleAgreementService saleAgreementService;

    // Class under test
    @InjectMocks
    private SaleAgreementController saleAgreementController;

    // Test data
    private User mockUser;
    private SaleAgreementAddDto mockSaleAgreementAddDto;
    private SaleAgreementEditDto mockSaleAgreementEditDto;

    /**
     * Set up method to initialize mocks and test data before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mockUser, mockSaleAgreementAddDto, and mockSaleAgreementEditDto for testing
        mockUser = new User(/* initialize user fields */);
        // Initializing fields for SaleAgreementAddDto
        mockSaleAgreementAddDto = new SaleAgreementAddDto(
                123,  // Number of agreement
                LocalDate.now(),  // Date of agreement
                LocalDate.now(),  // Date of registration of agreement
                LocalDate.now(),  // Date of supplies
                null,  // Type of the sale (replace with actual SaleType)
                new Buyer(/* initialize Buyer fields */),
                List.of(new Product(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                new User(/* initialize User fields */)
        );

        // Initializing fields for SaleAgreementEditDto
        mockSaleAgreementEditDto = new SaleAgreementEditDto(
                456L,  // Id (Sales agreement id in date base)
                789,  // Number of agreement
                LocalDate.now(),  // Date of agreement
                LocalDate.now(),  // Date of registration of agreement
                LocalDate.now(),  // Date of supplies
                null,  // Type of the sale (replace with actual SaleType)
                new Buyer(/* initialize Buyer fields */),
                List.of(new Product(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                new User(/* initialize User fields */)
        );
    }

    /**
     * Test case for the allSaleAgreements method in the SaleAgreementController class.
     */
    @Test
    void testAllSaleAgreements() {
        // When
        String result = saleAgreementController.allSaleAgreements();

        // Then
        assertEquals("saleAgreement", result);
    }

    /**
     * Test case for the getAllSaleAgreementsForUser method in the SaleAgreementController class.
     */
    @Test
    void testGetAllSaleAgreementsForUser() {
        // Given
        List<Agreement> mockAgreements = Collections.singletonList(new Agreement(/* initialize agreement fields */));
        when(saleAgreementService.findAll(any(User.class))).thenReturn(mockAgreements);

        // When
        ResponseEntity<?> response = saleAgreementController.getAllSaleAgreementsForUser(mockUser);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAgreements, response.getBody());
        verify(saleAgreementService, times(1)).findAll(mockUser);
    }
    /**
     * Test case for the getAllSaleAgreements method in the SaleAgreementController class.
     */
    @Test
    void testGetAllSaleAgreements() {
        // Given
        List<Agreement> mockAgreements = Collections.singletonList(new Agreement(/* initialize agreement fields */));
        when(saleAgreementService.findAll()).thenReturn(mockAgreements);

        // When
        ResponseEntity<?> response = saleAgreementController.getAllSaleAgreements();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAgreements, response.getBody());
        verify(saleAgreementService, times(1)).findAll();
    }

    /**
     * Test case for the addSaleAgreement method in the SaleAgreementController class.
     */
    @Test
    void testAddSaleAgreement() {
        // When
        ResponseEntity<?> response = saleAgreementController.addSaleAgreement(mockUser, mockSaleAgreementAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(saleAgreementService, times(1)).addAgreement(mockSaleAgreementAddDto, mockUser);
    }

    /**
     * Test case for the editAgreement method in the SaleAgreementController class.
     */
    @Test
    void testEditAgreement() {
        // When
        ResponseEntity<?> response = saleAgreementController.editAgreement(mockUser, mockSaleAgreementEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(saleAgreementService, times(1)).editAgreement(mockSaleAgreementEditDto, mockUser);
    }
}