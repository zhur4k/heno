package com.heno.dto.mapper;

import com.heno.dto.PartnerToShowInListDto;
import com.heno.model.Partner;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting Partner to PartnerToShowInListDto.
 */
@Service
public class PartnerToShowInListDtoMapper implements Function<Partner, PartnerToShowInListDto> {
    /**
     * Converts a SaleAgreementAddDto object to an Agreement entity.
     *
     * @param partner The Agreement object to convert.
     * @return An PartnerToShowInListDto converted from the Partner.
     */
    @Override
    public PartnerToShowInListDto apply(Partner partner) {
        return new PartnerToShowInListDto(
                partner.getId(),
                partner.getName(),
                partner.getAddress(),
                partner.getUNP(),
                partner.getCheckingAccount(),
                partner.getPhoneNumber(),
                partner.getEmail()
        );
    }
}
