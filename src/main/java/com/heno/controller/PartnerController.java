package com.heno.controller;

import com.heno.dto.PartnerAddDto;
import com.heno.dto.PartnerEditDto;
import com.heno.service.PartnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing Partners.
 */
@RestController
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerService partnerService;



    /**
     * Constructs a PartnerController with the specified PartnerService dependency.
     *
     * @param partnerService The service for managing partners.
     */
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }
    /**
     * Endpoint to get all partners page.
     *
     * @return HTML page to view list of all partners.
     */
    @GetMapping
    String allPartnersPage() {
        return "partners";
    }

    /**
     * Endpoint for retrieving all partners.
     *
     * @return ResponseEntity containing the list of partners or an error message.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPartners() {
        try {
            return ResponseEntity.ok(partnerService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint for adding a new partner.
     *
     * @param partnerAddDto   The DTO containing information for creating the partner.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addPartner(
            @RequestBody PartnerAddDto partnerAddDto
    ) {
        try {
            partnerService.addPartner(partnerAddDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for editing an existing partner.
     *
     * @param partnerEditDto     The DTO containing information for editing the partner.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editPartner(
            @RequestBody PartnerEditDto partnerEditDto
    ) {
        try {
            partnerService.editPartner(partnerEditDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
