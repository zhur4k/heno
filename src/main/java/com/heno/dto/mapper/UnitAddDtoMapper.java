package com.heno.dto.mapper;

import com.heno.dto.ProductAddDto;
import com.heno.dto.UnitAddDto;
import com.heno.model.Product;
import com.heno.model.Unit;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting UnitAddDto object to Unit entity.
 */
@Service
public class UnitAddDtoMapper implements Function<UnitAddDto, Unit> {
    /**
     * Converts a UnitAddDto object to Unit entity.
     *
     * @param unitAddDto The unitAddDto object to convert.
     * @return A Unit converted from the unitAddDto.
     */
    @Override
    public Unit apply(UnitAddDto unitAddDto) {
        Unit unit = new Unit();
        unit.setName(unitAddDto.name());
        return unit;
    }
}
