package com.heno.service;

import com.heno.dto.*;
import com.heno.dto.mapper.*;
import com.heno.model.*;
import com.heno.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ProductService class.
 */
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductAddDtoMapper productAddDtoMapper;

    @Mock
    private ProductEditDtoMapper productEditDtoMapper;
    @Mock
    private ProductToShowInListDtoMapper productToShowInListDtoMapper;

    @InjectMocks
    private ProductService productService;

    private ProductAddDto productAddDto;
    private ProductEditDto productEditDto;

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
     * Test case for the findAll method of ProductService.
     */
    @Test
    void testFindAll() {
        // Given
        List<Product> mockProducts = Collections.singletonList(new Product());
        when(productRepository.findAll())
                .thenReturn(mockProducts);

        // When
        List<ProductToShowInListDto> result = productService.findAll();

        // Then
        assertEquals(mockProducts
                .stream()
                        .map(productToShowInListDtoMapper).collect(Collectors.toList()),
                result);
        verify(productRepository, times(1)).findAll();
    }

    /**
     * Test case for the addProduct method of ProductService.
     */
    @Test
    void testAddProduct() {
        // Given
        Product mockProduct = Mockito.mock(Product.class);
        when(productAddDtoMapper.apply(any(ProductAddDto.class))).thenReturn(mockProduct);

        // When
        productService.addProduct(productAddDto);

        // Then
        verify(productAddDtoMapper, times(1)).apply(productAddDto);
        verify(productRepository, times(1)).save(mockProduct);
    }

    /**
     * Test case for the editProduct method of ProductService.
     */
    @Test
    void testEditProduct() {
        // Given
        Product mockProduct = Mockito.mock(Product.class);
        when(productEditDtoMapper.apply(any(ProductEditDto.class))).thenReturn(mockProduct);

        // When
        productService.editProduct(productEditDto);
        // Then
        verify(productEditDtoMapper, times(1)).apply(productEditDto);
        verify(productRepository, times(1)).save(mockProduct);
    }
}
