package com.heno.dto;

public record CurrencyToShowInListDto(
        /**
         * Field "Id(Unit id in date base)"
         */
        Long id,
        /**
         * Field "Letter code"
         */
        String Cur_Abbreviation,
        /**
         * Field "Name of currency"
         */
        String Cur_Name
) {
}
