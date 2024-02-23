package com.heno.dto;

import com.heno.model.*;

public record ProductAddDto(
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
