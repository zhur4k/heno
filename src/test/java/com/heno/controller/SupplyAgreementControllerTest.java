package com.heno.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

/**
 * Unit tests for the SupplyAgreementController class.
 */
class SupplyAgreementControllerTest {

    // Mock for dependency
    @Mock
    private SupplyAgreementService supplyAgreementService;

    // Class under test
    @InjectMocks
    private SupplyAgreementController supplyAgreementController;

//     Test data
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
        mockUser = new User();
        // Initializing fields for SupplyAgreementAddDto
        supplyAgreementAddDto = new SupplyAgreementAddDto(
                123,  // Number of agreement
                LocalDate.now(),  // Date of agreement
                LocalDate.now(),  // Date of registration of agreement
                LocalDate.now(),  // Date of supplies
                null,  // Type of the sale (replace with actual SaleType)
                new Partner(/* initialize Buyer fields */),
                List.of(new AgreementProduct(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                null
        );

        // Initializing fields for SupplyAgreementEditDto
        supplyAgreementEditDto = new SupplyAgreementEditDto(
                456L,  // Id (Supply agreement id in date base)
                789,  // Number of agreement
                LocalDate.now(),  // Date of agreement
                LocalDate.now(),  // Date of registration of agreement
                LocalDate.now(),  // Date of supplies
                null,  // Type of the Supply (replace with actual SaleType)
                new Partner(/* initialize Buyer fields */),
                List.of(new AgreementProduct(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                new User(/* initialize User fields */)
        );
    }

    /**
     * Test case for the allSupplyAgreementsPage method in the SupplyAgreementController class.
     * Tests the successful retrieval of the supply agreements page.
     */
    @Test
    void testAllSupplyAgreementsPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = supplyAgreementController.allSupplyAgreementsPage();

        // Then
        assertEquals("supplyAgreements", result);
    }

    /**
     * Test case for the addSupplyAgreementPage method in the SupplyAgreementController class.
     * Tests the successful retrieval of the add supply agreement page.
     */
    @Test
    void testAddSupplyAgreementPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = supplyAgreementController.addSupplyAgreementPage();

        // Then
        assertEquals("addSupplyAgreement", result);
    }

    /**
     * Test case for the editSupplyAgreementPage method in the SupplyAgreementController class.
     * Tests the successful retrieval of the edit supply agreement page.
     */
    @Test
    void testEditSupplyAgreementPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = supplyAgreementController.editSupplyAgreementPage();

        // Then
        assertEquals("editSupplyAgreement", result);
    }

    /**
     * Test case for the getAllSupplyAgreementsForUser method in the SupplyAgreementController class.
     * Tests the successful retrieval of all supply agreements for a user.
     */
    @Test
    void testGetAllSupplyAgreementsForUser_Success() {
        // Given
        when(supplyAgreementService.findAll(any(User.class))).thenReturn(null);

        // When
        ResponseEntity<?> response = supplyAgreementController.getAllSupplyAgreementsForUser(mock(User.class));

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(supplyAgreementService, times(1)).findAll(any(User.class));
    }

    /**
     * Test case for the getAllSupplyAgreementsForUser method in the SupplyAgreementController class.
     * Tests an exception scenario when an error occurs while retrieving all supply agreements for a user.
     */
    @Test
    void testGetAllSupplyAgreementsForUser_Exception() {
        // Given
        when(supplyAgreementService.findAll(any(User.class))).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = supplyAgreementController.getAllSupplyAgreementsForUser(mock(User.class));

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(supplyAgreementService, times(1)).findAll(any(User.class));
    }

    /**
     * Test case for the addSupplyAgreement method in the SupplyAgreementController class.
     * Tests the successful addition of a supply agreement.
     */
    @Test
    void testAddSupplyAgreement_Success() {
        // Given
        doNothing().when(supplyAgreementService).addAgreement(any(), any(User.class));

        // When
        ResponseEntity<?> response = supplyAgreementController.addSupplyAgreement(mock(User.class), supplyAgreementAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(supplyAgreementService, times(1)).addAgreement(any(), any(User.class));
    }

    /**
     * Test case for the addSupplyAgreement method in the SupplyAgreementController class.
     * Tests an exception scenario when an error occurs while adding a supply agreement.
     */
    @Test
    void testAddSupplyAgreement_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(supplyAgreementService).addAgreement(any(), any(User.class));

        // When
        ResponseEntity<?> response = supplyAgreementController.addSupplyAgreement(mock(User.class),supplyAgreementAddDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(supplyAgreementService, times(1)).addAgreement(any(), any(User.class));
    }

    /**
     * Test case for the editSupplyAgreement method in the SupplyAgreementController class.
     * Tests the successful editing of a supply agreement.
     */
    @Test
    void testEditSupplyAgreement_Success() {
        // Given
        doNothing().when(supplyAgreementService).editAgreement(any(), any(User.class));

        // When
        ResponseEntity<?> response = supplyAgreementController.editSupplyAgreement(mock(User.class), supplyAgreementEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(supplyAgreementService, times(1)).editAgreement(any(), any(User.class));
    }

    /**
     * Test case for the editSupplyAgreement method in the SupplyAgreementController class.
     * Tests an exception scenario when an error occurs while editing a supply agreement.
     */
    @Test
    void testEditSupplyAgreement_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(supplyAgreementService).editAgreement(any(), any(User.class));

        // When
        ResponseEntity<?> response = supplyAgreementController.editSupplyAgreement(mock(User.class), supplyAgreementEditDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(supplyAgreementService, times(1)).editAgreement(any(), any(User.class));
    }
}