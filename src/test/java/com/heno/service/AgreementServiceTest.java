package com.heno.service;

import com.heno.dto.AgreementToShowInListDto;
import com.heno.dto.mapper.AgreementToShowInListDtoMapper;
import com.heno.model.*;
import com.heno.repository.AgreementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AgreementService class.
 */
class AgreementServiceTest {

    @Mock
    private AgreementRepository agreementRepository;

    @Mock
    private AgreementToShowInListDtoMapper agreementToShowInListDtoMapper;

    @InjectMocks
    private AgreementService agreementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the findAll method of AgreementService.
     */
    @Test
    void testFindAll() {
        // Given
        List<Agreement> mockAgreements = Collections.singletonList(new Agreement(/* initialize agreement fields */));
        when(agreementRepository.findAll())
                .thenReturn(mockAgreements);

        // When
        List<AgreementToShowInListDto> result = agreementService.findAll();

        // Then
        assertEquals(mockAgreements
                .stream()
                        .map(agreementToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(agreementRepository, times(1)).findAll();
    }
}
