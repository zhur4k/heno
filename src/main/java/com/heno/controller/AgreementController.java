package com.heno.controller;

import com.heno.service.AgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing agreements.
 */
@RestController
@RequestMapping("/agreements")
public class AgreementController {

    private final AgreementService agreementService;

    /**
     * Constructs a AgreementController with the specified AgreementService dependency.
     *
     * @param agreementService The service for managing agreements.
     */
    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    /**
     * Endpoint to get all agreements page.
     *
     * @return HTML page to view list of agreements.
     */
    @GetMapping
    String allAgreementsPage() {
        return "agreements";
    }

    /**
     * Endpoint for retrieving all agreements.
     *
     * @return ResponseEntity containing the list of agreements or an error message.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAgreements() {
        try {
            return ResponseEntity.ok(agreementService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
