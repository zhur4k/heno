package com.heno.controller;

import com.heno.dto.UnitAddDto;
import com.heno.dto.UnitEditDto;
import com.heno.service.UnitService;
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

/**
 * Unit tests for the UnitController class.
 */
class UnitControllerTest {

    // Mocks for dependencies
    @Mock
    private UnitService unitService;

    // Class under test
    @InjectMocks
    private UnitController unitController;

    // Test data
    private UnitAddDto unitAddDto;
    private UnitEditDto unitEditDto;

    /**
     * Set up method to initialize mocks and test data before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        unitAddDto = new UnitAddDto(
                "123"
        );
        unitEditDto = new UnitEditDto(
                1L,
                "123"
        );
    }
    /**
     * Test case for the allUnits method in the UnitController class.
     */
    @Test
    void testAllUnits() {
        // When
        String result = unitController.allUnitsPage();

        // Then
        assertEquals("units", result);
    }
    /**
     * Test case for the getAllUnits method in the UnitController class.
     */
    @Test
    void testGetAllUnits() {
        // When
        ResponseEntity<?> response = unitController.getAllUnits();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(unitService, times(1)).findAll();
    }
    /**
     * Test case for the addUnit method in the UnitController class.
     */
    @Test
    void testAddUnit() {
        // When
        ResponseEntity<?> response = unitController.addUnit(unitAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(unitService, times(1)).addUnit(unitAddDto);
    }

    /**
     * Test case for the editUnit method in the UnitController class.
     */
    @Test
    void testEditUnit() {
        // When
        ResponseEntity<?> response = unitController.editUnit(unitEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(unitService, times(1)).editUnit(unitEditDto);
    }

}