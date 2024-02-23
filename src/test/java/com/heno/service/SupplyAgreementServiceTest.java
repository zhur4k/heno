package com.heno.service;

import com.heno.dto.*;
import com.heno.dto.mapper.AgreementToShowInListDtoMapper;
import com.heno.dto.mapper.SupplyAgreementAddDtoMapper;
import com.heno.dto.mapper.SupplyAgreementEditDtoMapper;
import com.heno.model.*;
import com.heno.repository.AgreementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the SupplyAgreementService class.
 */
class SupplyAgreementServiceTest {

    @Mock
    private AgreementRepository agreementRepository;

    @Mock
    private SupplyAgreementAddDtoMapper supplyAgreementAddDtoMapper;

    @Mock
    private SupplyAgreementEditDtoMapper supplyAgreementEditDtoMapper;
    @Mock
    private AgreementToShowInListDtoMapper agreementToShowInListDtoMapper;

    @InjectMocks
    private SupplyAgreementService supplyAgreementService;

    private User mockEmployee;
    private SupplyAgreementAddDto supplyAgreementAddDto;
    private SupplyAgreementEditDto supplyAgreementEditDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mockEmployee, mockSupplyAgreementAddDto, and mockSupplyAgreementEditDto for testing
        mockEmployee = new User(/* initialize user fields */);
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
                456L,  // Id (Supplies agreement id in date base)
                789,  // Number of agreement
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
    }

    /**
     * Test case for the findAll method of SupplyAgreementService.
     */
    @Test
    void testFindAllByEmployee() {
        // Given
        List<Agreement> mockAgreements = Collections.singletonList(new Agreement(/* initialize agreement fields */));
        when(agreementRepository.findAllByEmployeeAndTypeOfAgreement(any(User.class), any(String.class)))
                .thenReturn(mockAgreements);

        // When
        List<AgreementToShowInListDto> result = supplyAgreementService.findAll(mockEmployee);

        // Then
        assertEquals(mockAgreements
                .stream()
                        .map(agreementToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(agreementRepository, times(1)).findAllByEmployeeAndTypeOfAgreement(mockEmployee, "supply");
    }

    /**
     * Test case for the addAgreement method of SupplyAgreementService.
     */
    @Test
    void testAddAgreement() {
        // Given
        Agreement mockAgreement = Mockito.mock(Agreement.class);
        when(supplyAgreementAddDtoMapper.apply(any(SupplyAgreementAddDto.class))).thenReturn(mockAgreement);

        // When
        supplyAgreementService.addAgreement(supplyAgreementAddDto, mockEmployee);

        // Then
        verify(supplyAgreementAddDtoMapper, times(1)).apply(supplyAgreementAddDto);
        verify(mockAgreement, times(1)).setEmployee(mockEmployee);
        verify(agreementRepository, times(1)).save(mockAgreement);
    }

    /**
     * Test case for the editAgreement method of SupplyAgreementService.
     */
    @Test
    void testEditAgreement() {
        // Given
        Agreement mockAgreement = Mockito.mock(Agreement.class);
        when(supplyAgreementEditDtoMapper.apply(any(SupplyAgreementEditDto.class))).thenReturn(mockAgreement);

        // When
        supplyAgreementService.editAgreement(supplyAgreementEditDto, mockEmployee);
        // Then
        verify(supplyAgreementEditDtoMapper, times(1)).apply(supplyAgreementEditDto);
        verify(mockAgreement, times(1)).setEmployee(mockEmployee);
        verify(agreementRepository, times(1)).save(mockAgreement);
    }
}
