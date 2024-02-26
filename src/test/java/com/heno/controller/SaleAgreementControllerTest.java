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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                new Partner(/* initialize Buyer fields */),
                List.of(new AgreementProduct(/* initialize Product fields */)),
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
                new Partner(/* initialize Buyer fields */),
                List.of(new AgreementProduct(/* initialize Product fields */)),
                null,  // Currency of agreement (replace with actual AgreementCurrency)
                List.of(new PaymentDate(/* initialize PaymentDate fields */)),
                new Shipment(/* initialize Shipment fields */),
                new User(/* initialize User fields */)
        );
    }

    /**
     * Test case for the editSaleAgreementPage method in the SaleAgreementController class.
     * Tests the successful retrieval of the edit sale agreement page.
     */
    @Test
    void testEditSaleAgreementPage() throws Exception {
        // Given
        // No additional setup needed for this test case

        // When
        String result = saleAgreementController.editSaleAgreementPage();

        // Then
        assertEquals("editSaleAgreement", result);
    }

    /**
     * Test case for the addSaleAgreementPage method in the SaleAgreementController class.
     * Tests the successful retrieval of the add sale agreement page.
     */
    @Test
    void testAddSaleAgreementPage() throws Exception {
        // Given
        // No additional setup needed for this test case

        // When
        String result = saleAgreementController.addSaleAgreementPage();

        // Then
        assertEquals("addSaleAgreement", result);
    }

    /**
     * Test case for the allSaleAgreementsPage method in the SaleAgreementController class.
     * Tests the successful retrieval of the sale agreements page.
     */
    @Test
    void testAllSaleAgreements() throws Exception {
        // Given
        // No additional setup needed for this test case

        // When
        String result = saleAgreementController.allSaleAgreementsPage();

        // Then
        assertEquals("saleAgreements", result);
    }

    /**
     * Test case for the getAllSaleAgreementsForUser method in the SaleAgreementController class.
     * Tests the successful retrieval of sale agreements for a user.
     */
    @Test
    void testGetAllSaleAgreementsForUser_Success() throws Exception {
        // Given
        when(saleAgreementService.findAll(mockUser)).thenReturn(null);

        // When
        ResponseEntity<?> response = saleAgreementController.getAllSaleAgreementsForUser(mockUser);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(saleAgreementService, times(1)).findAll(mockUser);
    }

    /**
     * Test case for the getAllSaleAgreementsForUser method in the SaleAgreementController class.
     * Tests an exception scenario when an error occurs while retrieving sale agreements for a user.
     */
    @Test
    void testGetAllSaleAgreementsForUser_Exception() throws Exception {
        // Given
        when(saleAgreementService.findAll(mockUser)).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = saleAgreementController.getAllSaleAgreementsForUser(mockUser);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(saleAgreementService, times(1)).findAll(mockUser);
    }

    /**
     * Test case for the addSaleAgreement method in the SaleAgreementController class.
     * Tests the successful addition of a sale agreement.
     */
    @Test
    void testAddSaleAgreement_Success() throws Exception {
        // Given
        doNothing().when(saleAgreementService).addAgreement(mockSaleAgreementAddDto, mockUser);

        // When
        ResponseEntity<?> response = saleAgreementController.addSaleAgreement(mockUser, mockSaleAgreementAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(saleAgreementService, times(1)).addAgreement(mockSaleAgreementAddDto, mockUser);
    }

    /**
     * Test case for the addSaleAgreement method in the SaleAgreementController class.
     * Tests an exception scenario when an error occurs while adding a sale agreement.
     */
    @Test
    void testAddSaleAgreement_Exception() throws Exception {
        // Given
        doThrow(new RuntimeException("Some error message")).when(saleAgreementService).addAgreement(mockSaleAgreementAddDto, mockUser);

        // When
        ResponseEntity<?> response = saleAgreementController.addSaleAgreement(mockUser, mockSaleAgreementAddDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(saleAgreementService, times(1)).addAgreement(mockSaleAgreementAddDto, mockUser);
    }

    /**
     * Test case for the getSaleAgreement method in the SaleAgreementController class.
     * Tests the successful retrieval of a sale agreement.
     */
    @Test
    void testGetSaleAgreement_Success() throws Exception {
        // Given
        when(saleAgreementService.getAgreement(1L, mockUser)).thenReturn(null);

        // When
        ResponseEntity<?> response = saleAgreementController.getSaleAgreement(mockUser, 1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(saleAgreementService, times(1)).getAgreement(1L, mockUser);
    }

    /**
     * Test case for the getSaleAgreement method in the SaleAgreementController class.
     * Tests an exception scenario when an error occurs while retrieving a sale agreement.
     */
    @Test
    void testGetSaleAgreement_Exception() throws Exception {
        // Given
        when(saleAgreementService.getAgreement(1L, mockUser)).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = saleAgreementController.getSaleAgreement(mockUser, 1L);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(saleAgreementService, times(1)).getAgreement(1L, mockUser);
    }

    /**
     * Test case for the editSaleAgreement method in the SaleAgreementController class.
     * Tests the successful editing of a sale agreement.
     */
    @Test
    void testEditSaleAgreement_Success() throws Exception {
        // Given
        doNothing().when(saleAgreementService).editAgreement(mockSaleAgreementEditDto, mockUser);

        // When
        ResponseEntity<?> response = saleAgreementController.editSaleAgreement(mockUser, mockSaleAgreementEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(saleAgreementService, times(1)).editAgreement(mockSaleAgreementEditDto, mockUser);
    }

    /**
     * Test case for the editSaleAgreement method in the SaleAgreementController class.
     * Tests an exception scenario when an error occurs while editing a sale agreement.
     */
    @Test
    void testEditSaleAgreement_Exception() throws Exception {
        // Given
        doThrow(new RuntimeException("Some error message")).when(saleAgreementService).editAgreement(mockSaleAgreementEditDto, mockUser);

        // When
        ResponseEntity<?> response = saleAgreementController.editSaleAgreement(mockUser, mockSaleAgreementEditDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(saleAgreementService, times(1)).editAgreement(mockSaleAgreementEditDto, mockUser);
    }
}