package com.heno.dto.mapper;

import com.heno.dto.ProductEditDto;
import com.heno.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting ProductEditDto object to Product entity.
 */
@Service
public class ProductEditDtoMapper implements Function<ProductEditDto, Product> {
    /**
     * Converts a ProductEditDto object to Product entity.
     *
     * @param productEditDto The productEditDto object to convert.
     * @return A Product converted from the productEditDto.
     */
    @Override
    public Product apply(ProductEditDto productEditDto) {
        Product product = new Product();
        product.setId(productEditDto.id());
        product.setName(productEditDto.name());
        product.setUnit(productEditDto.unit());
        return product;
    }
}
