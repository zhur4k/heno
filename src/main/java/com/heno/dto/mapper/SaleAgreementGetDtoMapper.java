package com.heno.dto.mapper;

import com.heno.dto.SaleAgreementGetDto;
import com.heno.model.Agreement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting Agreement to SaleAgreementGetDto.
 */
@Service
public class SaleAgreementGetDtoMapper implements Function<Agreement, SaleAgreementGetDto> {

    /**
     * Converts a Agreement object to an SaleAgreementGetDto.
     *
     * @param agreement The Agreement object to convert.
     * @return An SaleAgreementGetDto entity converted from the Agreement.
     */
    @Override
    public SaleAgreementGetDto apply(Agreement agreement) {
        return new SaleAgreementGetDto(
                agreement.getId(),
                agreement.getNumber(),
                agreement.getDateOfAgreement(),
                agreement.getDateOfRegistrationAgreement(),
                agreement.getDateOfSupplies(),
                agreement.getSaleType(),
                agreement.getPartner(),
                agreement.getProducts(),
                agreement.getCurrency(),
                agreement.getPaymentDate(),
                agreement.getShipment(),
                agreement.getEmployee()
        );
    }
}
