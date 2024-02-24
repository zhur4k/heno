package com.heno.service;

import com.heno.dto.*;
import com.heno.dto.mapper.PartnerAddDtoMapper;
import com.heno.dto.mapper.PartnerEditDtoMapper;
import com.heno.dto.mapper.PartnerToShowInListDtoMapper;
import com.heno.model.Partner;
import com.heno.repository.PartnerRepository;
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
 * Partner tests for the PartnerService class.
 */
class PartnerServiceTest {

    @Mock
    private PartnerRepository partnerRepository;

    @Mock
    private PartnerAddDtoMapper partnerAddDtoMapper;

    @Mock
    private PartnerEditDtoMapper partnerEditDtoMapper;
    @Mock
    private PartnerToShowInListDtoMapper partnerToShowInListDtoMapper;

    @InjectMocks
    private PartnerService partnerService;

    private PartnerAddDto partnerAddDto;
    private PartnerEditDto partnerEditDto;

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
     * Test case for the findAll method of PartnerService.
     */
    @Test
    void testFindAll() {
        // Given
        List<Partner> mockPartners = Collections.singletonList(new Partner());
        when(partnerRepository.findAll())
                .thenReturn(mockPartners);

        // When
        List<PartnerToShowInListDto> result = partnerService.findAll();

        // Then
        assertEquals(mockPartners
                .stream()
                        .map(partnerToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(partnerRepository, times(1)).findAll();
    }

    /**
     * Test case for the addPartner method of PartnerService.
     */
    @Test
    void testAddPartner() {
        // Given
        Partner mockPartner = Mockito.mock(Partner.class);
        when(partnerAddDtoMapper.apply(any(PartnerAddDto.class))).thenReturn(mockPartner);

        // When
        partnerService.addPartner(partnerAddDto);

        // Then
        verify(partnerAddDtoMapper, times(1)).apply(partnerAddDto);
        verify(partnerRepository, times(1)).save(mockPartner);
    }

    /**
     * Test case for the editPartner method of PartnerService.
     */
    @Test
    void testEditPartner() {
        // Given
        Partner mockPartner = Mockito.mock(Partner.class);
        when(partnerEditDtoMapper.apply(any(PartnerEditDto.class))).thenReturn(mockPartner);

        // When
        partnerService.editPartner(partnerEditDto);
        // Then
        verify(partnerEditDtoMapper, times(1)).apply(partnerEditDto);
        verify(partnerRepository, times(1)).save(mockPartner);
    }
}
