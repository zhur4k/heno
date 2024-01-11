package com.heno.service;

import com.heno.model.AgreementCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyConversionServiceIntegrationTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testRealRequestToApi() {
        // Replace with your actual currency code
        String currencyCode = "431";

        // Make a real request to the NB RB API
        ResponseEntity<AgreementCurrency> responseEntity = restTemplate.getForEntity("https://api.nbrb.by/exrates/rates/"+currencyCode, AgreementCurrency.class);

        // Assert that the response is successful (HTTP 2xx status)
        assertNotNull(responseEntity.getBody());

        // Print the exchange rate for demonstration purposes
        AgreementCurrency agreementCurrency = responseEntity.getBody();
        System.out.println(agreementCurrency);
    }
}
