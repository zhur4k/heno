package com.heno.dto.mapper;

import com.heno.dto.ProductAddDto;
import com.heno.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting ProductAddDto object to Product entity.
 */
@Service
public class ProductAddDtoMapper implements Function<ProductAddDto, Product> {
    /**
     * Converts a ProductAddDto object to Product entity.
     *
     * @param productAddDto The productAddDto object to convert.
     * @return A Product converted from the productAddDto.
     */
    @Override
    public Product apply(ProductAddDto productAddDto) {
        Product product = new Product();
        product.setName(productAddDto.name());
        product.setUnit(productAddDto.unit());
        return product;
    }
}
