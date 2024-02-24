package com.heno.controller;

import com.heno.dto.ProductAddDto;
import com.heno.dto.ProductEditDto;
import com.heno.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a ProductsController with the specified ProductsService dependency.
     *
     * @param productService The service for managing products.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint to get all products page.
     *
     * @return HTML page to view list of all products.
     */
    @GetMapping
    String allProductsPage() {
        return "products";
    }

    /**
     * Endpoint for retrieving all products.
     *
     * @return ResponseEntity containing the list of products or an error message.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {
        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint for adding a new product.
     *
     * @param productAddDto   The DTO containing information for creating the product.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestBody ProductAddDto productAddDto
    ) {
        try {
            productService.addProduct(productAddDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for editing an existing product.
     *
     * @param productEditDto     The DTO containing information for editing the product.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editProduct(
            @RequestBody ProductEditDto productEditDto
    ) {
        try {
            productService.editProduct(productEditDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
