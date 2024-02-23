package com.heno.dto.mapper;

import com.heno.dto.SupplyAgreementEditDto;
import com.heno.model.Agreement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting SupplyAgreementEditDto to Agreement entity.
 */
@Service
public class SupplyAgreementEditDtoMapper implements Function<SupplyAgreementEditDto, Agreement> {

    /**
     * Converts a SupplyAgreementEditDto object to an Agreement entity.
     *
     * @param supplyAgreementEditDto The SupplyAgreementEditDto object to convert.
     * @return An Agreement entity converted from the SupplyAgreementEditDto.
     */
    @Override
    public Agreement apply(SupplyAgreementEditDto supplyAgreementEditDto) {
        Agreement agreement = new Agreement();

        agreement.setId(supplyAgreementEditDto.id());
        agreement.setNumber(supplyAgreementEditDto.number());
        agreement.setDateOfAgreement(supplyAgreementEditDto.dateOfAgreement());
        agreement.setDateOfRegistrationAgreement(supplyAgreementEditDto.dateOfRegistrationAgreement());
        agreement.setDateOfSupplies(supplyAgreementEditDto.dateOfSupplies());
        agreement.setSaleType(supplyAgreementEditDto.saleType());
        agreement.setBuyer(supplyAgreementEditDto.buyer());
        agreement.setProducts(supplyAgreementEditDto.products());
        agreement.setCurrency(supplyAgreementEditDto.currency());
        agreement.setPaymentDate(supplyAgreementEditDto.paymentDate());
        agreement.setShipment(supplyAgreementEditDto.shipment());
        agreement.setEmployee(supplyAgreementEditDto.employee());
        return agreement;
    }
}
