package com.heno.controller;

import com.heno.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing currencies.
 */
@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    /**
     * Constructs a CurrenciesController with the specified CurrenciesService dependency.
     *
     * @param currencyService The service for managing currencies.
     */
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Endpoint to get all currencies page.
     *
     * @return HTML page to view list of all currencies.
     */
    @GetMapping
    String allCurrenciesPage() {
        return "currencies";
    }

    /**
     * Endpoint for retrieving all view currencies.
     *
     * @return ResponseEntity containing the list of view currencies or an error message.
     */
    @GetMapping("/getAllView")
    public ResponseEntity<?> getAllViewCurrencies() {
        try {
            return ResponseEntity.ok(currencyService.findAllView());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    /**
     * Endpoint for retrieving all currencies.
     *
     * @return ResponseEntity containing the list of currencies or an error message.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCurrencies() {
        try {
            return ResponseEntity.ok(currencyService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    /**
     * Endpoint for adding a new currency.
     *
     * @param id   Id of currency.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addCurrency(
            @RequestBody Long id
    ) {
        try {
            currencyService.addCurrency(id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for delete an existing currencies.
     *
     * @param id     Id of the currency.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteCurrency(
            @RequestBody Long id
    ) {
        try {
            currencyService.deleteCurrency(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
