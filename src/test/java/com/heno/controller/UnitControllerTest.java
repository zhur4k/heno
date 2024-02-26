package com.heno.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

/**
 * Unit tests for the UnitController class.
 */
class UnitControllerTest {

    // Mock for dependency
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
     * Test case for the allUnitsPage method in the UnitController class.
     * Tests the successful retrieval of the units page.
     */
    @Test
    void testAllUnitsPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = unitController.allUnitsPage();

        // Then
        assertEquals("units", result);
    }

    /**
     * Test case for the getAllUnits method in the UnitController class.
     * Tests the successful retrieval of all units.
     */
    @Test
    void testGetAllUnits_Success() {
        // Given
        when(unitService.findAll()).thenReturn(null);

        // When
        ResponseEntity<?> response = unitController.getAllUnits();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(unitService, times(1)).findAll();
    }

    /**
     * Test case for the getAllUnits method in the UnitController class.
     * Tests an exception scenario when an error occurs while retrieving all units.
     */
    @Test
    void testGetAllUnits_Exception() {
        // Given
        when(unitService.findAll()).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = unitController.getAllUnits();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(unitService, times(1)).findAll();
    }

    /**
     * Test case for the addUnit method in the UnitController class.
     * Tests the successful addition of a unit.
     */
    @Test
    void testAddUnit_Success() {
        // Given
        doNothing().when(unitService).addUnit(any());

        // When
        ResponseEntity<?> response = unitController.addUnit(unitAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(unitService, times(1)).addUnit(any());
    }

    /**
     * Test case for the addUnit method in the UnitController class.
     * Tests an exception scenario when an error occurs while adding a unit.
     */
    @Test
    void testAddUnit_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(unitService).addUnit(any());

        // When
        ResponseEntity<?> response = unitController.addUnit(unitAddDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(unitService, times(1)).addUnit(any());
    }

    /**
     * Test case for the editUnit method in the UnitController class.
     * Tests the successful editing of a unit.
     */
    @Test
    void testEditUnit_Success() {
        // Given
        doNothing().when(unitService).editUnit(any());

        // When
        ResponseEntity<?> response = unitController.editUnit(unitEditDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(unitService, times(1)).editUnit(any());
    }

    /**
     * Test case for the editUnit method in the UnitController class.
     * Tests an exception scenario when an error occurs while editing a unit.
     */
    @Test
    void testEditUnit_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(unitService).editUnit(any());

        // When
        ResponseEntity<?> response = unitController.editUnit(unitEditDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(unitService, times(1)).editUnit(any());
    }
}