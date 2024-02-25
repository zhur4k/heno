package com.heno.service;

import com.heno.dto.CurrencyToShowInListDto;
import com.heno.dto.mapper.CurrencyToShowInListDtoMapper;
import com.heno.model.AgreementCurrency;
import com.heno.repository.AgreementCurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Currency tests for the CurrencyService class.
 */
class CurrencyServiceTest {

    @Mock
    private AgreementCurrencyRepository agreementCurrencyRepository;
    @Mock
    private CurrencyToShowInListDtoMapper currencyToShowInListDtoMapper;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the findAll method of CurrencyService.
     */
    @Test
    void testFindAll() {
        // Given
        List<AgreementCurrency> currencies = Collections.singletonList(new AgreementCurrency());
        when(agreementCurrencyRepository.findAll())
                .thenReturn(currencies);

        // When
        List<CurrencyToShowInListDto> result = currencyService.findAll();

        // Then
        assertEquals(currencies
                .stream()
                        .map(currencyToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(agreementCurrencyRepository, times(1)).findAll();
    }
    /**
     * Test case for the findAllView method of CurrencyService.
     */
    @Test
    void testFindAllView() {
        // Given
        List<AgreementCurrency> currencies = Collections.singletonList(new AgreementCurrency());
        when(agreementCurrencyRepository.findAllByViewTrue())
                .thenReturn(currencies);

        // When
        List<CurrencyToShowInListDto> result = currencyService.findAllView();

        // Then
        assertEquals(currencies
                .stream()
                        .map(currencyToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(agreementCurrencyRepository, times(1)).findAllByViewTrue();
    }

    /**
     * Test case for the addCurrency method of CurrencyService.
     */
    @Test
    void testAddCurrency() {
        Long id = 1L;
        // Given
        AgreementCurrency currency = new AgreementCurrency();
        currency.setId(id);
        currency.setView(false); // Устанавливаем начальное значение view

        when(agreementCurrencyRepository.findById(id)).thenReturn(Optional.of(currency));


        // When
        currencyService.addCurrency(id);

        // Then
        assertEquals(currency.getView(),true);
        verify(agreementCurrencyRepository, times(1)).findById(id);
        verify(agreementCurrencyRepository, times(1)).save(currency);
    }

    /**
     * Test case for the deleteCurrency method of CurrencyService.
     */
    @Test
    void testDeleteCurrency() {
        Long id = 1L;
        // Given
        AgreementCurrency currency = new AgreementCurrency();
        currency.setId(id);
        currency.setView(false); // Устанавливаем начальное значение view

        when(agreementCurrencyRepository.findById(id)).thenReturn(Optional.of(currency));


        // When
        currencyService.deleteCurrency(id);

        // Then
        assertEquals(currency.getView(),false);
        verify(agreementCurrencyRepository, times(1)).findById(id);
        verify(agreementCurrencyRepository, times(1)).save(currency);
    }
}
