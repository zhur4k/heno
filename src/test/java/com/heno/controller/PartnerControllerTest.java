package com.heno.controller;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PartnerControllerTest {

    // Mocks for dependencies
    @Mock
    private PartnerService partnerService;

    // Class under test
    @InjectMocks
    private PartnerController partnerController;

    // Test data
    private PartnerAddDto partnerAddDto;
    private PartnerEditDto partnerEditDto;

    /**
     * Set up method to initialize mocks and test data before each test.
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
     * Test case for the allPartners method in the PartnerController class.
     */
    @Test
    void testAllPartners() {
        // When
        String result = partnerController.allPartnersPage();

        // Then
        assertEquals("partners", result);
    }
    /**
     * Test case for the getAllPartners method in the PartnerController class.
     */
    @Test
    void testGetAllPartners() {
        // When
        ResponseEntity<?> response = partnerController.getAllPartners();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(partnerService, times(1)).findAll();
    }
    /**
     * Test case for the addPartner method in the PartnerController class.
     */
    @Test
    void testAddPartner() {
        // When
        ResponseEntity<?> response = partnerController.addPartner(partnerAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(partnerService, times(1)).addPartner(partnerAddDto);
    }

    /**
     * Test case for the editPartner method in the PartnerController class.
     */
    @Test
    void testEditPartner() {
        // When
        ResponseEntity<?> response = partnerController.editPartner(partnerEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(partnerService, times(1)).editPartner(partnerEditDto);
    }

}