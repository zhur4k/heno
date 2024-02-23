package com.heno.dto.mapper;

import com.heno.dto.UnitEditDto;
import com.heno.model.Unit;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting UnitAddDto object to Unit entity.
 */
@Service
public class UnitEditDtoMapper implements Function<UnitEditDto, Unit> {
    /**
     * Converts a UnitAddDto object to Unit entity.
     *
     * @param unitEditDto The unitEditDto object to convert.
     * @return A Unit converted from the unitEditDto.
     */
    @Override
    public Unit apply(UnitEditDto unitEditDto) {
        Unit unit = new Unit();
        unit.setId(unitEditDto.id());
        unit.setName(unitEditDto.name());
        return unit;
    }
}
