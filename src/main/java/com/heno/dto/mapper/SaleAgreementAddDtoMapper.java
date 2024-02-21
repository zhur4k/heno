package com.heno.dto.mapper;

import com.heno.dto.SaleAgreementAddDto;
import com.heno.model.Agreement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting SaleAgreementAddDto to Agreement entity.
 */
@Service
public class SaleAgreementAddDtoMapper implements Function<SaleAgreementAddDto, Agreement> {

    /**
     * Converts a SaleAgreementAddDto object to an Agreement entity.
     *
     * @param saleAgreementAddDto The SaleAgreementAddDto object to convert.
     * @return An Agreement entity converted from the SaleAgreementAddDto.
     */
    @Override
    public Agreement apply(SaleAgreementAddDto saleAgreementAddDto) {
        Agreement agreement = new Agreement();
        agreement.setNumber(saleAgreementAddDto.number());
        agreement.setDateOfAgreement(saleAgreementAddDto.dateOfAgreement());
        agreement.setDateOfRegistrationAgreement(saleAgreementAddDto.dateOfRegistrationAgreement());
        agreement.setDateOfSupplies(saleAgreementAddDto.dateOfSupplies());
        agreement.setTypeOfAgreement("sale");
        agreement.setSaleType(saleAgreementAddDto.saleType());
        agreement.setBuyer(saleAgreementAddDto.buyer());
        agreement.setProducts(saleAgreementAddDto.products());
        agreement.setCurrency(saleAgreementAddDto.currency());
        agreement.setPaymentDate(saleAgreementAddDto.paymentDate());
        agreement.setShipment(saleAgreementAddDto.shipment());
        return agreement;
    }
}
