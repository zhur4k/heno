package com.heno.service;

import com.heno.dto.SaleAgreementAddDto;
import com.heno.dto.SaleAgreementEditDto;
import com.heno.dto.mapper.SaleAgreementAddDtoMapper;
import com.heno.dto.mapper.SaleAgreementEditDtoMapper;
import com.heno.model.*;
import com.heno.repository.AgreementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @InjectMocks
    private SaleAgreementService saleAgreementService;

    private User mockEmployee;
    private SaleAgreementAddDto mockSaleAgreementAddDto;
    private SaleAgreementEditDto mockSaleAgreementEditDto;

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
                new Buyer(/* initialize Buyer fields */),
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
                new Buyer(/* initialize Buyer fields */),
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
        List<Agreement> result = saleAgreementService.findAll(mockEmployee);

        // Then
        assertEquals(mockAgreements, result);
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
    /**
     * Test for {@link SaleAgreementService#findAll()}.
     * It verifies that the service correctly calls the repository's findAllByTypeOfAgreement method
     * with the expected argument and returns the result.
     */
    @Test
    void testFindAll() {
        // Arrange
        User dummyUser = new User(); // create a dummy user
        String expectedTypeOfAgreement = "sale";

        // create dummy agreements
        List<Agreement> expectedAgreements = Arrays.asList(
                new Agreement(/* provide necessary parameters */),
                new Agreement(/* provide necessary parameters */)
        );

        // Mock the repository response
        when(agreementRepository.findAllByTypeOfAgreement(expectedTypeOfAgreement))
                .thenReturn(expectedAgreements);

        // Act
        List<Agreement> result = saleAgreementService.findAll();

        // Assert
        // Verify that the repository method was called with the expected argument
        verify(agreementRepository, times(1)).findAllByTypeOfAgreement(expectedTypeOfAgreement);

        // Verify that the result matches the expected agreements
        assertEquals(expectedAgreements, result);
    }
}
