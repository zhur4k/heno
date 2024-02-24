package com.heno.dto.mapper;

import com.heno.dto.PartnerEditDto;
import com.heno.model.Partner;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting PartnerAddDto object to Partner entity.
 */
@Service
public class PartnerEditDtoMapper implements Function<PartnerEditDto, Partner> {
    /**
     * Converts a PartnerAddDto object to Partner entity.
     *
     * @param partnerEditDto The partnerEditDto object to convert.
     * @return A Partner converted from the partnerEditDto.
     */
    @Override
    public Partner apply(PartnerEditDto partnerEditDto) {
        Partner partner = new Partner();
        partner.setId(partnerEditDto.id());
        partner.setAddress(partnerEditDto.address());
        partner.setName(partnerEditDto.name());
        partner.setEmail(partnerEditDto.email());
        partner.setCheckingAccount(partnerEditDto.checkingAccount());
        partner.setPhoneNumber(partnerEditDto.phoneNumber());
        partner.setUNP(partnerEditDto.UNP());
        return partner;
    }
}
