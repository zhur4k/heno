package com.heno.controller;

import com.heno.dto.ProductAddDto;
import com.heno.dto.ProductEditDto;
import com.heno.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the ProductController class.
 */
class ProductControllerTest {

    // Mocks for dependencies
    @Mock
    private ProductService productService;

    // Class under test
    @InjectMocks
    private ProductController productController;

    // Test data
    private ProductAddDto productAddDto;
    private ProductEditDto productEditDto;

    /**
     * Set up method to initialize mocks and test data before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productAddDto = new ProductAddDto(
                "123",
                    null

        );
        productEditDto = new ProductEditDto(
                1L,
                "123",
                null
        );
    }
    /**
     * Test case for the allProducts method in the ProductController class.
     */
    @Test
    void testAllProducts() {
        // When
        String result = productController.allProductsPage();

        // Then
        assertEquals("products", result);
    }
    /**
     * Test case for the getAllProducts method in the ProductController class.
     */
    @Test
    void testGetAllProducts() {
        // When
        ResponseEntity<?> response = productController.getAllProducts();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).findAll();
    }
    /**
     * Test case for the addProduct method in the ProductController class.
     */
    @Test
    void testAddProduct() {
        // When
        ResponseEntity<?> response = productController.addProduct(productAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, times(1)).addProduct(productAddDto);
    }

    /**
     * Test case for the editProduct method in the ProductController class.
     */
    @Test
    void testEditProduct() {
        // When
        ResponseEntity<?> response = productController.editProduct(productEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, times(1)).editProduct(productEditDto);
    }

}