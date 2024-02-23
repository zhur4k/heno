package com.heno.controller;

import com.heno.dto.SupplyAgreementAddDto;
import com.heno.dto.SupplyAgreementEditDto;
import com.heno.model.*;
import com.heno.service.SupplyAgreementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the SupplyAgreementController class.
 */
class SupplyAgreementControllerTest {

    // Mocks for dependencies
    @Mock
    private SupplyAgreementService supplyAgreementService;

    // Class under test
    @InjectMocks
    private SupplyAgreementController supplyAgreementController;

    // Test data
    private User mockUser;
    private SupplyAgreementAddDto supplyAgreementAddDto;
    private SupplyAgreementEditDto supplyAgreementEditDto;

    /**
     * Set up method to initialize mocks and test data before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mockUser, mockSupplyAgreementAddDto, and mockSupplyAgreementEditDto for testing
        mockUser = new User(/* initialize user fields */);
        // Initializing fields for SupplyAgreementAddDto
        supplyAgreementAddDto = new SupplyAgreementAddDto(
                123,  // Number of agreement
                LocalDate.now(),  // Date of agreement
                LocalDate.now(),  // Date of registration of agreement
                LocalDate.now(),  // Date of supplies
                null,  // Type of the sale (replace with actual SaleType)
                new Buyer(/* initialize Buyer fields */),
                List.of(new AgreementProduct(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                new User(/* initialize User fields */)
        );

        // Initializing fields for SupplyAgreementEditDto
        supplyAgreementEditDto = new SupplyAgreementEditDto(
                456L,  // Id (Supply agreement id in date base)
                789,  // Number of agreement
                LocalDate.now(),  // Date of agreement
                LocalDate.now(),  // Date of registration of agreement
                LocalDate.now(),  // Date of supplies
                null,  // Type of the Supply (replace with actual SaleType)
                new Buyer(/* initialize Buyer fields */),
                List.of(new AgreementProduct(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                new User(/* initialize User fields */)
        );
    }

    /**
     * Test case for the allSupplyAgreements method in the SupplyAgreementController class.
     */
    @Test
    void testAllSupplyAgreements() {
        // When
        String result = supplyAgreementController.allSupplyAgreementsPage();

        // Then
        assertEquals("supplyAgreements", result);
    }
    /**
     * Test case for the addSupplyAgreements method in the SupplyAgreementController class.
     */
    @Test
    void testAddSupplyAgreementPage() {
        // When
        String result = supplyAgreementController.addSupplyAgreementPage();

        // Then
        assertEquals("addSupplyAgreement", result);
    }
    /**
     * Test case for the allSupplyAgreements method in the SupplyAgreementController class.
     */
    @Test
    void testEditSupplyAgreementPage() {
        // When
        String result = supplyAgreementController.editSupplyAgreementPage();

        // Then
        assertEquals("editSupplyAgreement", result);
    }
    /**
     * Test case for the getAllSupplyAgreementsForUser method in the SupplyAgreementController class.
     */
    @Test
    void testGetAllSupplyAgreementsForUser() {
        // When
        ResponseEntity<?> response = supplyAgreementController.getAllSupplyAgreementsForUser(mockUser);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(supplyAgreementService, times(1)).findAll(mockUser);
    }
//    /**
//     * Test case for the getAllSaleAgreements method in the SaleAgreementController class.
//     */
//    @Test
//    void testGetAllSaleAgreements() {
//        // Given
//        List<Agreement> mockAgreements = Collections.singletonList(new Agreement(/* initialize agreement fields */));
//        when(saleAgreementService.findAll()).thenReturn(mockAgreements);
//
//        // When
//        ResponseEntity<?> response = saleAgreementController.getAllSaleAgreements();
//
//        // Then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(mockAgreements, response.getBody());
//        verify(saleAgreementService, times(1)).findAll();
//    }

    /**
     * Test case for the addSupplyAgreement method in the SupplyAgreementController class.
     */
    @Test
    void testAddSupplyAgreement() {
        // When
        ResponseEntity<?> response = supplyAgreementController.addSupplyAgreement(mockUser, supplyAgreementAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(supplyAgreementService, times(1)).addAgreement(supplyAgreementAddDto, mockUser);
    }

    /**
     * Test case for the editSupplyAgreement method in the SupplyAgreementController class.
     */
    @Test
    void testEditSupplyAgreement() {
        // When
        ResponseEntity<?> response = supplyAgreementController.editSupplyAgreement(mockUser, supplyAgreementEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(supplyAgreementService, times(1)).editAgreement(supplyAgreementEditDto, mockUser);
    }
}