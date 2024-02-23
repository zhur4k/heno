package com.heno.dto.mapper;

import com.heno.dto.UnitToShowInListDto;
import com.heno.model.Unit;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting Agreement to AgreementToShowInListDto.
 */
@Service
public class UnitToShowInListDtoMapper implements Function<Unit, UnitToShowInListDto> {
    /**
     * Converts a UnitToShowInListDto object to a Unit entity.
     *
     * @param unit The Unit object to convert.
     * @return An UnitToShowInListDto converted from the Unit.
     */
    @Override
    public UnitToShowInListDto apply(Unit unit) {
        return new UnitToShowInListDto(
                unit.getId(),
                unit.getName()
        );
    }
}
