package com.heno.dto.mapper;

import com.heno.dto.SupplyAgreementAddDto;
import com.heno.model.Agreement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting SupplyAgreementAddDto to Agreement entity.
 */
@Service
public class SupplyAgreementAddDtoMapper implements Function<SupplyAgreementAddDto, Agreement> {

    /**
     * Converts a SupplyAgreementAddDto object to an Agreement entity.
     *
     * @param supplyAgreementAddDto The SupplyAgreementAddDto object to convert.
     * @return An Agreement entity converted from the SupplyAgreementAddDto.
     */
    @Override
    public Agreement apply(SupplyAgreementAddDto supplyAgreementAddDto) {
        Agreement agreement = new Agreement();
        agreement.setNumber(supplyAgreementAddDto.number());
        agreement.setDateOfAgreement(supplyAgreementAddDto.dateOfAgreement());
        agreement.setDateOfRegistrationAgreement(supplyAgreementAddDto.dateOfRegistrationAgreement());
        agreement.setDateOfSupplies(supplyAgreementAddDto.dateOfSupplies());
        agreement.setTypeOfAgreement("supply");
        agreement.setState(true);
        agreement.setSaleType(supplyAgreementAddDto.saleType());
        agreement.setPartner(supplyAgreementAddDto.partner());
        agreement.setProducts(supplyAgreementAddDto.products());
        agreement.setCurrency(supplyAgreementAddDto.currency());
        agreement.setPaymentDate(supplyAgreementAddDto.paymentDate());
        agreement.setShipment(supplyAgreementAddDto.shipment());
        return agreement;
    }
}
