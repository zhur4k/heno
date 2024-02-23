package com.heno.dto.mapper;

import com.heno.dto.ProductToShowInListDto;
import com.heno.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Mapper class for converting Product to ProductToShowInListDto.
 */
@Service
public class ProductToShowInListDtoMapper implements Function<Product, ProductToShowInListDto> {
    /**
     * Converts a Product object to ProductToShowInListDto.
     *
     * @param product The Product object to convert.
     * @return An ProductToShowInListDto converted from the Product.
     */
    @Override
    public ProductToShowInListDto apply(Product product) {
        return new ProductToShowInListDto(
                product.getId(),
                product.getName(),
                product.getUnit()
        );
    }
}
