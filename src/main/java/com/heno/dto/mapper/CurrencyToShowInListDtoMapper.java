package com.heno.dto.mapper;

import com.heno.dto.CurrencyToShowInListDto;
import com.heno.model.AgreementCurrency;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting AgreementCurrency to CurrencyToShowInListDto.
 */
@Service
public class CurrencyToShowInListDtoMapper implements Function<AgreementCurrency,CurrencyToShowInListDto> {
    /**
     * Converts a CurrencyAddDto object to an AgreementCurrency entity.
     *
     * @param agreementCurrency The AgreementCurrency object to convert.
     * @return An CurrencyToShowInListDto converted from the AgreementCurrency.
     */
    @Override
    public CurrencyToShowInListDto apply(AgreementCurrency agreementCurrency) {
        return new CurrencyToShowInListDto(
                agreementCurrency.getId(),
                agreementCurrency.getCur_Abbreviation(),
                agreementCurrency.getCur_Name()
        );
    }
}
