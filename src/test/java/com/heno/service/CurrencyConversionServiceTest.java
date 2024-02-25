package com.heno.service;

import com.heno.model.AgreementCurrency;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for CurrencyConversionService.
 */
public class CurrencyConversionServiceTest {

    @Test
    public void testConvertToBYN() {
        // Mocking the RestTemplate
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CurrencyConversionService currencyConversionService = new CurrencyConversionService(restTemplateMock);

        // Creating a sample AgreementCurrency for testing
        AgreementCurrency fromCurrency = new AgreementCurrency();
        fromCurrency.setCur_ID("431");
        fromCurrency.setCur_date(LocalDate.now());
        fromCurrency.setCur_Scale(new BigDecimal("1.0"));
        fromCurrency.setCur_OfficialRate(new BigDecimal("2.0"));

        // Mocking the response from the NB RB API
        Mockito.when(restTemplateMock.getForObject(
                        "https://api.nbrb.by/exrates/rates/431",
                        AgreementCurrency.class))
                .thenReturn(fromCurrency);

        // Test conversion to BYN
        BigDecimal convertedAmount = currencyConversionService.convertToBYN(new BigDecimal("10.0"), fromCurrency);
        assertEquals(new BigDecimal("20.00"), convertedAmount, "Conversion to BYN is incorrect.");
    }

    @Test
    public void testConvertFromBYN() {
        // Mocking the RestTemplate
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CurrencyConversionService currencyConversionService = new CurrencyConversionService(restTemplateMock);

        // Creating a sample AgreementCurrency for testing
        AgreementCurrency toCurrency = new AgreementCurrency();
        toCurrency.setCur_ID("432");
        toCurrency.setCur_date(LocalDate.now());
        toCurrency.setCur_Scale(new BigDecimal("1.0"));
        toCurrency.setCur_OfficialRate(new BigDecimal("1.5"));

        // Mocking the response from the NB RB API
        Mockito.when(restTemplateMock.getForObject(
                        "https://api.nbrb.by/exrates/rates/432",
                        AgreementCurrency.class))
                .thenReturn(toCurrency);

        // Test conversion from BYN
        BigDecimal convertedAmount = currencyConversionService.convertFromBYN(new BigDecimal("30.0"), toCurrency);
        assertEquals(new BigDecimal("20.0"), convertedAmount, "Conversion from BYN is incorrect.");
    }

    @Test
    public void testGetExchangeCurrency_Cached() {
        // Mocking the RestTemplate
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CurrencyConversionService currencyConversionService = new CurrencyConversionService(restTemplateMock);

        // Creating a sample AgreementCurrency for testing
        AgreementCurrency cachedCurrency = new AgreementCurrency();
        cachedCurrency.setCur_ID("431");
        cachedCurrency.setCur_date(LocalDate.now());

        // Test getting cached currency
        AgreementCurrency resultCurrency = currencyConversionService.getExchangeCurrency(cachedCurrency);
        assertEquals(cachedCurrency, resultCurrency, "The cached currency should be returned.");
        Mockito.verify(restTemplateMock, Mockito.never()).getForObject(Mockito.anyString(), Mockito.eq(AgreementCurrency.class));
    }

    @Test
    public void testGetExchangeCurrency_FetchFromAPI() {
        // Mocking the RestTemplate
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CurrencyConversionService currencyConversionService = new CurrencyConversionService(restTemplateMock);

        // Creating a sample AgreementCurrency for testing
        AgreementCurrency uncachedCurrency = new AgreementCurrency();
        uncachedCurrency.setCur_ID("431");
        uncachedCurrency.setCur_date(LocalDate.now().minusDays(1)); // Not today
        // Creating a sample AgreementCurrency for testing
        AgreementCurrency uncachedCurrency2 = new AgreementCurrency();
        uncachedCurrency2.setCur_ID("431");
        uncachedCurrency2.setCur_date(LocalDate.now()); // Not today

        // Mocking the response from the NB RB API
        Mockito.when(restTemplateMock.getForObject(
                        "https://api.nbrb.by/exrates/rates/431",
                        AgreementCurrency.class))
                .thenReturn(uncachedCurrency2);

        // Test fetching uncached currency from API
        AgreementCurrency resultCurrency = currencyConversionService.getExchangeCurrency(uncachedCurrency);
        assertEquals(uncachedCurrency2, resultCurrency, "The currency should be fetched from the API.");
        Mockito.verify(restTemplateMock, Mockito.times(1)).getForObject(Mockito.anyString(), Mockito.eq(AgreementCurrency.class));
    }
    @Test
    public void testGetExchangeCurrency_APIError() {
        // Mocking the RestTemplate
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CurrencyConversionService currencyConversionService = new CurrencyConversionService(restTemplateMock);

        // Creating a sample AgreementCurrency for testing
        AgreementCurrency requestCurrency = new AgreementCurrency();
        requestCurrency.setCur_ID("431");
        requestCurrency.setCur_date(LocalDate.now().minusDays(1));

        // Mocking an API error response (null)
        Mockito.when(restTemplateMock.getForObject(
                        "https://api.nbrb.by/exrates/431",
                        AgreementCurrency.class))
                .thenReturn(null);

        // Test handling API error
        try {
            currencyConversionService.getExchangeCurrency(requestCurrency);
            // If we reach here, the test should fail because an exception is expected
            fail("Expected RuntimeException, but no exception was thrown.");
        } catch (RuntimeException e) {
            // Expected exception, assert the error message or perform additional checks
            assertEquals("Failed to retrieve exchange rate from NB RB API.", e.getMessage(), "Unexpected error message.");
        }

        // Verify that the RestTemplate was called once
        Mockito.verify(restTemplateMock, Mockito.times(1)).getForObject(Mockito.anyString(), Mockito.eq(AgreementCurrency.class));
    }

}
