package com.heno.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.heno.dto.PartnerAddDto;
import com.heno.dto.PartnerEditDto;
import com.heno.service.PartnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for the PartnerController class.
 */
class PartnerControllerTest {

    // Mock for dependency
    @Mock
    private PartnerService partnerService;

    private PartnerAddDto partnerAddDto;
    private PartnerEditDto partnerEditDto;
    // Class under test
    @InjectMocks
    private PartnerController partnerController;

    /**
     * Set up method to initialize mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        partnerAddDto = new PartnerAddDto(
                "name",
                "address",
                15151,
                "sdfsdf",
                "+375291980473",
                "email"
        );
        partnerEditDto = new PartnerEditDto(
                1L,
                "name",
                "address",
                15151,
                "sdfsdf",
                "+375291980473",
                "email"
        );
    }

    /**
     * Test case for the allPartnersPage method in the PartnerController class.
     * Tests the successful retrieval of the partners page.
     */
    @Test
    void testAllPartnersPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = partnerController.allPartnersPage();

        // Then
        assertEquals("partners", result);
    }

    /**
     * Test case for the getAllPartners method in the PartnerController class.
     * Tests the successful retrieval of all partners.
     */
    @Test
    void testGetAllPartners_Success() {
        // Given
        when(partnerService.findAll()).thenReturn(null);

        // When
        ResponseEntity<?> response = partnerController.getAllPartners();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partnerService, times(1)).findAll();
    }

    /**
     * Test case for the getAllPartners method in the PartnerController class.
     * Tests an exception scenario when an error occurs while retrieving all partners.
     */
    @Test
    void testGetAllPartners_Exception() {
        // Given
        when(partnerService.findAll()).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = partnerController.getAllPartners();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(partnerService, times(1)).findAll();
    }

    /**
     * Test case for the addPartner method in the PartnerController class.
     * Tests the successful addition of a partner.
     */
    @Test
    void testAddPartner_Success() {
        // Given
        doNothing().when(partnerService).addPartner(any());

        // When
        ResponseEntity<?> response = partnerController.addPartner(partnerAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(partnerService, times(1)).addPartner(any());
    }

    /**
     * Test case for the addPartner method in the PartnerController class.
     * Tests an exception scenario when an error occurs while adding a partner.
     */
    @Test
    void testAddPartner_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(partnerService).addPartner(any());

        // When
        ResponseEntity<?> response = partnerController.addPartner(partnerAddDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(partnerService, times(1)).addPartner(any());
    }

    /**
     * Test case for the editPartner method in the PartnerController class.
     * Tests the successful editing of a partner.
     */
    @Test
    void testEditPartner_Success() {
        // Given
        doNothing().when(partnerService).editPartner(any());

        // When
        ResponseEntity<?> response = partnerController.editPartner(partnerEditDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partnerService, times(1)).editPartner(any());
    }

    /**
     * Test case for the editPartner method in the PartnerController class.
     * Tests an exception scenario when an error occurs while editing a partner.
     */
    @Test
    void testEditPartner_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(partnerService).editPartner(any());

        // When
        ResponseEntity<?> response = partnerController.editPartner(partnerEditDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(partnerService, times(1)).editPartner(any());
    }
}