package com.heno.controller;

import com.heno.dto.SaleAgreementAddDto;
import com.heno.dto.SaleAgreementEditDto;
import com.heno.model.User;
import com.heno.service.SaleAgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing sale agreements.
 */
@RestController
@RequestMapping("/agreements/sale")
public class SaleAgreementController {

    private final SaleAgreementService saleAgreementService;

    /**
     * Constructs a SaleAgreementController with the specified SaleAgreementService dependency.
     *
     * @param saleAgreementService The service for managing sale agreements.
     */
    public SaleAgreementController(SaleAgreementService saleAgreementService) {
        this.saleAgreementService = saleAgreementService;
    }

    /**
     * Endpoint to get all sale agreements page.
     *
     * @return HTML page to view list of all sale agreements.
     */
    @GetMapping
    String allSaleAgreementsPage() {
        return "saleAgreements";
    }
    /**
     * Endpoint to get page for add sale agreement.
     *
     * @return HTML page to add sale agreement.
     */
    @GetMapping("/add")
    String addSaleAgreementPage() {
        return "addSaleAgreement";
    }
    /**
     * Endpoint to get page for edit sale agreement.
     *
     * @return HTML page to edit sale agreement.
     */
    @GetMapping("/edit")
    String editSaleAgreementPage() {
        return "editSaleAgreement";
    }
    /**
     * Endpoint for retrieving all sale agreements associated with the authenticated user.
     *
     * @param employee The authenticated user.
     * @return ResponseEntity containing the list of sale agreements or an error message.
     */
    @GetMapping("/getAllForUser")
    public ResponseEntity<?> getAllSaleAgreementsForUser(@AuthenticationPrincipal User employee) {
        try {
            return ResponseEntity.ok(saleAgreementService.findAll(employee));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint for adding a new sale agreement.
     *
     * @param employee              The authenticated user.
     * @param saleAgreementAddDto   The DTO containing information for creating the sale agreement.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addSaleAgreement(
            @AuthenticationPrincipal User employee,
            @RequestBody SaleAgreementAddDto saleAgreementAddDto
    ) {
        try {
            saleAgreementService.addAgreement(saleAgreementAddDto, employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for editing an existing sale agreement.
     *
     * @param employee                 The authenticated user.
     * @param saleAgreementEditDto     The DTO containing information for editing the sale agreement.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editSaleAgreement(
            @AuthenticationPrincipal User employee,
            @RequestBody SaleAgreementEditDto saleAgreementEditDto
    ) {
        try {
            saleAgreementService.editAgreement(saleAgreementEditDto, employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
