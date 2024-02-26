package com.heno.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

/**
 * Unit tests for the ProductController class.
 */
class ProductControllerTest {

    // Mock for dependency
    @Mock
    private ProductService productService;

    // Class under test
    @InjectMocks
    private ProductController productController;
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
     * Test case for the allProductsPage method in the ProductController class.
     * Tests the successful retrieval of the products page.
     */
    @Test
    void testAllProductsPage() {
        // Given
        // No additional setup needed for this test case

        // When
        String result = productController.allProductsPage();

        // Then
        assertEquals("products", result);
    }

    /**
     * Test case for the getAllProducts method in the ProductController class.
     * Tests the successful retrieval of all products.
     */
    @Test
    void testGetAllProducts_Success() {
        // Given
        when(productService.findAll()).thenReturn(null);

        // When
        ResponseEntity<?> response = productController.getAllProducts();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).findAll();
    }

    /**
     * Test case for the getAllProducts method in the ProductController class.
     * Tests an exception scenario when an error occurs while retrieving all products.
     */
    @Test
    void testGetAllProducts_Exception() {
        // Given
        when(productService.findAll()).thenThrow(new RuntimeException("Some error message"));

        // When
        ResponseEntity<?> response = productController.getAllProducts();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(productService, times(1)).findAll();
    }

    /**
     * Test case for the addProduct method in the ProductController class.
     * Tests the successful addition of a product.
     */
    @Test
    void testAddProduct_Success() {
        // Given
        doNothing().when(productService).addProduct(any());

        // When
        ResponseEntity<?> response = productController.addProduct(productAddDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, times(1)).addProduct(any());
    }

    /**
     * Test case for the addProduct method in the ProductController class.
     * Tests an exception scenario when an error occurs while adding a product.
     */
    @Test
    void testAddProduct_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(productService).addProduct(any());

        // When
        ResponseEntity<?> response = productController.addProduct(productAddDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(productService, times(1)).addProduct(any());
    }

    /**
     * Test case for the editProduct method in the ProductController class.
     * Tests the successful editing of a product.
     */
    @Test
    void testEditProduct_Success() {
        // Given
        doNothing().when(productService).editProduct(any());

        // When
        ResponseEntity<?> response = productController.editProduct(productEditDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, times(1)).editProduct(any());
    }

    /**
     * Test case for the editProduct method in the ProductController class.
     * Tests an exception scenario when an error occurs while editing a product.
     */
    @Test
    void testEditProduct_Exception() {
        // Given
        doThrow(new RuntimeException("Some error message")).when(productService).editProduct(any());

        // When
        ResponseEntity<?> response = productController.editProduct(productEditDto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error message", response.getBody());
        verify(productService, times(1)).editProduct(any());
    }
}