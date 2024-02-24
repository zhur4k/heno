package com.heno.dto.mapper;

import com.heno.dto.SaleAgreementEditDto;
import com.heno.model.Agreement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting SaleAgreementEditDto to Agreement entity.
 */
@Service
public class SaleAgreementEditDtoMapper implements Function<SaleAgreementEditDto, Agreement> {

    /**
     * Converts a SaleAgreementEditDto object to an Agreement entity.
     *
     * @param saleAgreementEditDto The SaleAgreementEditDto object to convert.
     * @return An Agreement entity converted from the SaleAgreementEditDto.
     */
    @Override
    public Agreement apply(SaleAgreementEditDto saleAgreementEditDto) {
        Agreement agreement = new Agreement();

        agreement.setId(saleAgreementEditDto.id());
        agreement.setNumber(saleAgreementEditDto.number());
        agreement.setDateOfAgreement(saleAgreementEditDto.dateOfAgreement());
        agreement.setDateOfRegistrationAgreement(saleAgreementEditDto.dateOfRegistrationAgreement());
        agreement.setDateOfSupplies(saleAgreementEditDto.dateOfSupplies());
        agreement.setSaleType(saleAgreementEditDto.saleType());
        agreement.setPartner(saleAgreementEditDto.partner());
        agreement.setProducts(saleAgreementEditDto.products());
        agreement.setCurrency(saleAgreementEditDto.currency());
        agreement.setPaymentDate(saleAgreementEditDto.paymentDate());
        agreement.setShipment(saleAgreementEditDto.shipment());
        agreement.setEmployee(saleAgreementEditDto.employee());
        return agreement;
    }
}
