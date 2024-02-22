package com.heno.dto.mapper;

import com.heno.dto.AgreementToShowInListDto;
import com.heno.model.Agreement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting Agreement to AgreementToShowInListDto.
 */
@Service
public class AgreementToShowInListDtoMapper implements Function<Agreement,AgreementToShowInListDto> {
    /**
     * Converts a SaleAgreementAddDto object to an Agreement entity.
     *
     * @param agreement The Agreement object to convert.
     * @return An AgreementToShowInListDto converted from the Agreement.
     */
    @Override
    public AgreementToShowInListDto apply(Agreement agreement) {
        return new AgreementToShowInListDto(
                agreement.getId(),
                agreement.getNumber(),
                agreement.getDateOfAgreement(),
                agreement.getDateOfRegistrationAgreement(),
                agreement.getDateOfSupplies(),
                agreement.getSaleType(),
                agreement.getBuyer(),
                agreement.getCurrency(),
                agreement.getPaymentDate(),
                agreement.getShipment(),
                agreement.getEmployee()
        );
    }
}
