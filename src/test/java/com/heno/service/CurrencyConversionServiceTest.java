package com.heno.service;
import com.heno.model.AgreementCurrency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyConversionServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyConversionService currencyConversionService;

    @BeforeEach
    void setUp() {
        currencyConversionService = new CurrencyConversionService(restTemplate);
    }

    @Test
    void convertToBYN() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.50");
        AgreementCurrency fromCurrency = createMockCurrency("USD", "1", "US Dollar", null, null);

        // Mock the external API response
        when(restTemplate.getForObject(anyString(), eq(AgreementCurrency.class))).thenReturn(
                createMockCurrency("USD", "1", "US Dollar", new BigDecimal("1"), new BigDecimal("2.0")));

        // Act
        BigDecimal result = currencyConversionService.convertToBYN(amount, fromCurrency);

        // Assert
        assertEquals(new BigDecimal("201.000"), result);
    }

    @Test
    void convertFromBYN() {
        // Arrange
        BigDecimal amount = new BigDecimal("201.000");
        AgreementCurrency toCurrency = createMockCurrency("USD", "1", "US Dollar", null,null);

        // Mock the external API response
        when(restTemplate.getForObject(anyString(), eq(AgreementCurrency.class))).thenReturn(
                createMockCurrency("BYN", "2", "Belarusian Ruble", new BigDecimal("1"), new BigDecimal("2")));

        // Act
        BigDecimal result = currencyConversionService.convertFromBYN(amount, toCurrency);

        // Assert
        assertEquals(new BigDecimal("100.500"), result);
    }

    // Helper method to create a mock currency
    private AgreementCurrency createMockCurrency(String abbreviation, String id, String name, BigDecimal scale, BigDecimal rate) {
        AgreementCurrency currency = new AgreementCurrency();
        currency.setCur_Abbreviation(abbreviation);
        currency.setCur_ID(id);
        currency.setCur_Name(name);
        currency.setCur_Scale(scale);
        currency.setCur_OfficialRate(rate);
        return currency;
    }
}
