package com.heno.service;

import com.heno.dto.*;
import com.heno.dto.mapper.ProductAddDtoMapper;
import com.heno.dto.mapper.ProductEditDtoMapper;
import com.heno.dto.mapper.ProductToShowInListDtoMapper;
import com.heno.model.Product;
import com.heno.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductAddDtoMapper productAddDtoMapper;
    private final ProductEditDtoMapper productEditDtoMapper;
    private final ProductToShowInListDtoMapper productToShowInListDtoMapper;

    /**
     * Constructs a ProductService with the specified dependencies.
     *
     * @param productAddDtoMapper      Mapper for converting ProductAddDto to product.
     * @param productEditDtoMapper     Mapper for converting ProductEditDto to product.
     * @param productToShowInListDtoMapper Mapper for converting product to productToShowInListDto.
     * @param productRepository              The repository for managing products.
     */
    public ProductService(ProductRepository productRepository, ProductAddDtoMapper productAddDtoMapper, ProductEditDtoMapper productEditDtoMapper, ProductToShowInListDtoMapper productToShowInListDtoMapper) {
        this.productRepository = productRepository;
        this.productAddDtoMapper = productAddDtoMapper;
        this.productEditDtoMapper = productEditDtoMapper;
        this.productToShowInListDtoMapper = productToShowInListDtoMapper;
    }

    /**
     * Retrieves all ProductDto.
     *
     * @return A list of ProductDto.
     */
    public List<ProductToShowInListDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productToShowInListDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new product to the system.
     *
     * @param productAddDto The DTO containing information for creating the product.
     */
    public void addProduct(ProductAddDto productAddDto) {
        Product product = productAddDtoMapper.apply(productAddDto);
        productRepository.save(product);
    }

    /**
     * Edits an existing product in the system.
     *
     * @param productEditDto The DTO containing information for editing the product.
     */
    public void editProduct(ProductEditDto productEditDto) {
        Product product = productEditDtoMapper.apply(productEditDto);
        productRepository.save(product);
    }
}
