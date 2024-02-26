package com.heno.controller;

import com.heno.dto.UnitAddDto;
import com.heno.dto.UnitEditDto;
import com.heno.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing units.
 */
@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    /**
     * Constructs a unitsController with the specified unitsService dependency.
     *
     * @param unitService The service for managing units.
     */
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    /**
     * Endpoint to get all units page.
     *
     * @return HTML page to view list of all units.
     */
    @GetMapping
    String allUnitsPage() {
        return "units";
    }

    /**
     * Endpoint for retrieving all units.
     *
     * @return ResponseEntity containing the list of units or an error message.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUnits() {
        try {
            return ResponseEntity.ok(unitService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint for adding a new unit.
     *
     * @param unitAddDto   The DTO containing information for creating the unit.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addUnit(
            @RequestBody UnitAddDto unitAddDto
    ) {
        try {
            unitService.addUnit(unitAddDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for editing an existing unit.
     *
     * @param unitEditDto     The DTO containing information for editing the unit.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editUnit(
            @RequestBody UnitEditDto unitEditDto
    ) {
        try {
            unitService.editUnit(unitEditDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
