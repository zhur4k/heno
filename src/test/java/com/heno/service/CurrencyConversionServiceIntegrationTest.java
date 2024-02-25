package com.heno.service;

import com.heno.model.AgreementCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyConversionServiceIntegrationTest {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Test
    void testRealRequestToApi() {
        // Replace with your actual currency code
        AgreementCurrency currency = new AgreementCurrency();

        currency.setCur_date(LocalDate.now());
        currency.setCur_ID("431");

        AgreementCurrency agreementCurrency = currencyConversionService.getExchangeCurrency(currency);

        assertNotNull(agreementCurrency);

        System.out.println(agreementCurrency);
    }
    @Test
    void testRealRequestToApiGetAll() {

        List<AgreementCurrency> currencies = currencyConversionService.getAllCurrencies();

        assertNotNull(currencies);

        System.out.println(currencies);
    }
}
