package com.heno.service;

import com.heno.dto.AgreementToShowInListDto;
import com.heno.dto.SaleAgreementAddDto;
import com.heno.dto.SaleAgreementEditDto;
import com.heno.dto.SaleAgreementGetDto;
import com.heno.dto.mapper.AgreementToShowInListDtoMapper;
import com.heno.dto.mapper.SaleAgreementAddDtoMapper;
import com.heno.dto.mapper.SaleAgreementEditDtoMapper;
import com.heno.dto.mapper.SaleAgreementGetDtoMapper;
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
 * Unit tests for the SaleAgreementService class.
 */
class SaleAgreementServiceTest {

    @Mock
    private AgreementRepository agreementRepository;

    @Mock
    private SaleAgreementAddDtoMapper saleAgreementAddDtoMapper;

    @Mock
    private SaleAgreementEditDtoMapper saleAgreementEditDtoMapper;
    @Mock
    private SaleAgreementGetDtoMapper saleAgreementGetDtoMapper;
    @Mock
    private AgreementToShowInListDtoMapper agreementToShowInListDtoMapper;

    @InjectMocks
    private SaleAgreementService saleAgreementService;

    private User mockEmployee;
    private SaleAgreementAddDto mockSaleAgreementAddDto;
    private SaleAgreementEditDto mockSaleAgreementEditDto;
    private SaleAgreementGetDto mockSaleAgreementGetDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mockEmployee, mockSaleAgreementAddDto, and mockSaleAgreementEditDto for testing
        mockEmployee = new User(/* initialize user fields */);
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
        mockSaleAgreementGetDto = new SaleAgreementGetDto(
                123L,
                12131,
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
     * Test case for the findAll method of SaleAgreementService.
     */
    @Test
    void testFindAllByEmployee() {
        // Given
        List<Agreement> mockAgreements = Collections.singletonList(new Agreement(/* initialize agreement fields */));
        when(agreementRepository.findAllByEmployeeAndTypeOfAgreement(any(User.class), any(String.class)))
                .thenReturn(mockAgreements);

        // When
        List<AgreementToShowInListDto> result = saleAgreementService.findAll(mockEmployee);

        // Then
        assertEquals(mockAgreements
                .stream()
                        .map(agreementToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(agreementRepository, times(1)).findAllByEmployeeAndTypeOfAgreement(mockEmployee, "sale");
    }

    /**
     * Test case for the addAgreement method of SaleAgreementService.
     */
    @Test
    void testAddAgreement() {
        // Given
        Agreement mockAgreement = Mockito.mock(Agreement.class);
        when(saleAgreementAddDtoMapper.apply(any(SaleAgreementAddDto.class))).thenReturn(mockAgreement);

        // When
        saleAgreementService.addAgreement(mockSaleAgreementAddDto, mockEmployee);

        // Then
        verify(saleAgreementAddDtoMapper, times(1)).apply(mockSaleAgreementAddDto);
        verify(mockAgreement, times(1)).setEmployee(mockEmployee);
        verify(agreementRepository, times(1)).save(mockAgreement);
    }

    /**
     * Test case for the editAgreement method of SaleAgreementService.
     */
    @Test
    void testEditAgreement() {
        // Given
        Agreement mockAgreement = Mockito.mock(Agreement.class);
        when(saleAgreementEditDtoMapper.apply(any(SaleAgreementEditDto.class))).thenReturn(mockAgreement);

        // When
        saleAgreementService.editAgreement(mockSaleAgreementEditDto, mockEmployee);
        // Then
        verify(saleAgreementEditDtoMapper, times(1)).apply(mockSaleAgreementEditDto);
        verify(mockAgreement, times(1)).setEmployee(mockEmployee);
        verify(agreementRepository, times(1)).save(mockAgreement);
    }
    @Test
    void testGetAgreement() {
        // Given
        Long mockAgreementId = 123L;
        Agreement mockAgreement = Mockito.mock(Agreement.class);

        // Mocking repository response
        when(agreementRepository.findByIdAndTypeOfAgreementAndEmployee(mockAgreementId, "sale", mockEmployee))
                .thenReturn(mockAgreement);

        // Mocking mapper response
        when(saleAgreementGetDtoMapper.apply(mockAgreement)).thenReturn(mockSaleAgreementGetDto);

        // When
        SaleAgreementGetDto result = saleAgreementService.getAgreement(mockAgreementId, mockEmployee);

        // Then
        assertEquals(mockSaleAgreementGetDto, result);
        verify(agreementRepository, times(1)).findByIdAndTypeOfAgreementAndEmployee(mockAgreementId, "sale", mockEmployee);
        verify(saleAgreementGetDtoMapper, times(1)).apply(mockAgreement);
    }
}
