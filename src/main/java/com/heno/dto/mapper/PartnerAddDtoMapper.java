package com.heno.dto.mapper;

import com.heno.dto.PartnerAddDto;
import com.heno.model.Partner;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting PartnerAddDto object to Partner entity.
 */
@Service
public class PartnerAddDtoMapper implements Function<PartnerAddDto, Partner> {
    /**
     * Converts a PartnerAddDto object to Partner entity.
     *
     * @param partnerAddDto The partnerAddDto object to convert.
     * @return A Partner converted from the partnerAddDto.
     */
    @Override
    public Partner apply(PartnerAddDto partnerAddDto) {
        Partner partner = new Partner();
        partner.setAddress(partnerAddDto.address());
        partner.setName(partnerAddDto.name());
        partner.setEmail(partnerAddDto.email());
        partner.setCheckingAccount(partnerAddDto.checkingAccount());
        partner.setPhoneNumber(partnerAddDto.phoneNumber());
        partner.setUNP(partnerAddDto.UNP());
        return partner;
    }
}
