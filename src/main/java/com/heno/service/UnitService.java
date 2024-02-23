package com.heno.service;

import com.heno.dto.UnitAddDto;
import com.heno.dto.UnitEditDto;
import com.heno.dto.UnitToShowInListDto;
import com.heno.dto.mapper.UnitAddDtoMapper;
import com.heno.dto.mapper.UnitEditDtoMapper;
import com.heno.dto.mapper.UnitToShowInListDtoMapper;
import com.heno.model.Unit;
import com.heno.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final UnitAddDtoMapper unitAddDtoMapper;
    private final UnitEditDtoMapper unitEditDtoMapper;
    private final UnitToShowInListDtoMapper unitToShowInListDtoMapper;

    /**
     * Constructs a UnitService with the specified dependencies.
     *
     * @param unitAddDtoMapper      Mapper for converting UnitAddDto to unit.
     * @param unitEditDtoMapper     Mapper for converting UnitEditDto to unit.
     * @param unitToShowInListDtoMapper Mapper for converting unit to UnitToShowInListDto.
     * @param unitRepository              The repository for managing units.
     */
    public UnitService(UnitRepository unitRepository, UnitAddDtoMapper unitAddDtoMapper, UnitEditDtoMapper unitEditDtoMapper, UnitToShowInListDtoMapper unitToShowInListDtoMapper) {
        this.unitRepository = unitRepository;
        this.unitAddDtoMapper = unitAddDtoMapper;
        this.unitEditDtoMapper = unitEditDtoMapper;
        this.unitToShowInListDtoMapper = unitToShowInListDtoMapper;
    }

    /**
     * Retrieves all UnitDto.
     *
     * @return A list of UnitDto.
     */
    public List<UnitToShowInListDto> findAll() {
        return unitRepository.findAll()
                .stream()
                .map(unitToShowInListDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new unit to the system.
     *
     * @param unitAddDto The DTO containing information for creating the unit.
     */
    public void addUnit(UnitAddDto unitAddDto) {
        Unit unit = unitAddDtoMapper.apply(unitAddDto);
        unitRepository.save(unit);
    }

    /**
     * Edits an existing unit in the system.
     *
     * @param unitEditDto The DTO containing information for editing the unit.
     */
    public void editUnit(UnitEditDto unitEditDto) {
        Unit unit = unitEditDtoMapper.apply(unitEditDto);
        unitRepository.save(unit);
    }
}
