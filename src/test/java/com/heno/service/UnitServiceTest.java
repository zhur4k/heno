package com.heno.service;

import com.heno.dto.*;
import com.heno.dto.mapper.*;
import com.heno.model.Unit;
import com.heno.repository.UnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UnitService class.
 */
class UnitServiceTest {

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private UnitAddDtoMapper unitAddDtoMapper;

    @Mock
    private UnitEditDtoMapper unitEditDtoMapper;
    @Mock
    private UnitToShowInListDtoMapper unitToShowInListDtoMapper;

    @InjectMocks
    private UnitService unitService;

    private UnitAddDto unitAddDto;
    private UnitEditDto unitEditDto;

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
     * Test case for the findAll method of UnitService.
     */
    @Test
    void testFindAll() {
        // Given
        List<Unit> mockUnits = Collections.singletonList(new Unit());
        when(unitRepository.findAll())
                .thenReturn(mockUnits);

        // When
        List<UnitToShowInListDto> result = unitService.findAll();

        // Then
        assertEquals(mockUnits
                .stream()
                        .map(unitToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(unitRepository, times(1)).findAll();
    }

    /**
     * Test case for the addUnit method of UnitService.
     */
    @Test
    void testAddUnit() {
        // Given
        Unit mockUnit = Mockito.mock(Unit.class);
        when(unitAddDtoMapper.apply(any(UnitAddDto.class))).thenReturn(mockUnit);

        // When
        unitService.addUnit(unitAddDto);

        // Then
        verify(unitAddDtoMapper, times(1)).apply(unitAddDto);
        verify(unitRepository, times(1)).save(mockUnit);
    }

    /**
     * Test case for the editUnit method of UnitService.
     */
    @Test
    void testEditUnit() {
        // Given
        Unit mockUnit = Mockito.mock(Unit.class);
        when(unitEditDtoMapper.apply(any(UnitEditDto.class))).thenReturn(mockUnit);

        // When
        unitService.editUnit(unitEditDto);
        // Then
        verify(unitEditDtoMapper, times(1)).apply(unitEditDto);
        verify(unitRepository, times(1)).save(mockUnit);
    }
}
