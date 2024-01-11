package com.heno.service;
import com.heno.model.AgreementCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class CurrencyConversionService {

    private static final String NB_RB_API_URL = "https://api.nbrb.by/exrates/rates/";

    private final RestTemplate restTemplate;
    @Autowired
    public CurrencyConversionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Converts the given amount from the specified currency to Belarusian Ruble (BYN).
     *
     * @param amount       The amount to be converted.
     * @param fromCurrency The currency of the input amount.
     * @return The converted amount in BYN.
     */
    public BigDecimal convertToBYN(BigDecimal amount, AgreementCurrency fromCurrency) {
        AgreementCurrency currency = getExchangeCurrency(fromCurrency);

        // Implementation of conversion to BYN

        return amount.multiply(currency.getCur_OfficialRate().divide(currency.getCur_Scale(),RoundingMode.HALF_UP));
    }

    /**
     * Converts the given amount from Belarusian Ruble (BYN) to the specified currency.
     *
     * @param amount     The amount to be converted.
     * @param toCurrency The target currency for conversion.
     * @return The converted amount in the target currency.
     */
    public BigDecimal convertFromBYN(BigDecimal amount, AgreementCurrency toCurrency) {

        AgreementCurrency currency = getExchangeCurrency(toCurrency);

        // Implementation of conversion from BYN
        return amount.divide(currency.getCur_OfficialRate().divide(currency.getCur_Scale(),RoundingMode.HALF_UP), RoundingMode.HALF_UP);
    }

    /**
     * Retrieves the exchange rate for the specified currency from the NB RB API.
     *
     * @param fromCurrency The currency for which to fetch the exchange rate.
     * @return The exchange rate for the specified currency.
     */
    public AgreementCurrency getExchangeCurrency(AgreementCurrency fromCurrency){
        if(Objects.equals(fromCurrency.getCur_date(), LocalDate.now())){
            return fromCurrency;
        }

        // Request to NB RB API for obtaining the currency exchange rate
        AgreementCurrency currency = restTemplate.getForObject(
                NB_RB_API_URL+fromCurrency.getCur_ID(),
                AgreementCurrency.class);
        if (currency == null) {
            throw new RuntimeException("Failed to retrieve exchange rate from NB RB API.");
        }
        return currency;
    }
}
