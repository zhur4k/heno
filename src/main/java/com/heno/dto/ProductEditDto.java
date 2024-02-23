package com.heno.dto;

import com.heno.model.*;

public record ProductEditDto(
        /**
         * Field "Id(Product id in date base)"
         */
        Long id,
        /**
         * Field "Name"
         */
        String name,
        /**
         * Field "Unit"
         */
        Unit unit
) {
}
