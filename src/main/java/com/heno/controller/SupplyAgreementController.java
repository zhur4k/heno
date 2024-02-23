package com.heno.controller;

import com.heno.dto.SupplyAgreementAddDto;
import com.heno.dto.SupplyAgreementEditDto;
import com.heno.model.User;
import com.heno.service.SupplyAgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing supply agreements.
 */
@RestController
@RequestMapping("/agreements/supply")
public class SupplyAgreementController {

    private final SupplyAgreementService supplyAgreementService;

    /**
     * Constructs a SupplyAgreementController with the specified SupplyAgreementService dependency.
     *
     * @param supplyAgreementService The service for managing supply agreements.
     */
    public SupplyAgreementController(SupplyAgreementService supplyAgreementService) {
        this.supplyAgreementService = supplyAgreementService;
    }

    /**
     * Endpoint to get all supply agreements page.
     *
     * @return HTML page to view list of all supply agreements.
     */
    @GetMapping
    String allSupplyAgreementsPage() {
        return "supplyAgreements";
    }
    /**
     * Endpoint to get page for add supply agreement.
     *
     * @return HTML page to add supply agreement.
     */
    @GetMapping("/add")
    String addSupplyAgreementPage() {
        return "addSupplyAgreement";
    }
    /**
     * Endpoint to get page for edit supply agreement.
     *
     * @return HTML page to edit supply agreement.
     */
    @GetMapping("/edit")
    String editSupplyAgreementPage() {
        return "editSupplyAgreement";
    }

    /**
     * Endpoint for retrieving all supply agreements associated with the authenticated user.
     *
     * @param employee The authenticated user.
     * @return ResponseEntity containing the list of supply agreements or an error message.
     */
    @GetMapping("/getAllForUser")
    public ResponseEntity<?> getAllSupplyAgreementsForUser(@AuthenticationPrincipal User employee) {
        try {
            return ResponseEntity.ok(supplyAgreementService.findAll(employee));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint for adding a new supply agreement.
     *
     * @param employee              The authenticated user.
     * @param supplyAgreementAddDto   The DTO containing information for creating the supply agreement.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addSupplyAgreement(
            @AuthenticationPrincipal User employee,
            @RequestBody SupplyAgreementAddDto supplyAgreementAddDto
    ) {
        try {
            supplyAgreementService.addAgreement(supplyAgreementAddDto, employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for editing an existing supply agreement.
     *
     * @param employee                 The authenticated user.
     * @param supplyAgreementEditDto     The DTO containing information for editing the supply agreement.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editSupplyAgreement(
            @AuthenticationPrincipal User employee,
            @RequestBody SupplyAgreementEditDto supplyAgreementEditDto
    ) {
        try {
            supplyAgreementService.editAgreement(supplyAgreementEditDto, employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
