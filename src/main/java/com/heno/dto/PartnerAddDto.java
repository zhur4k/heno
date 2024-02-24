package com.heno.dto;

public record PartnerAddDto(
        /**
         * Field "Name"
         */
        String name,
        /**
         * Field "Address"
         */
        String address,
        /**
         * Field "UNP"
         */
        Integer UNP,
        /**
         * Field "Checking account"
         */
        String checkingAccount,
        /**
         * Field "Phone Number"
         */
        String phoneNumber,
        /**
         * Field "Email"
         */
        String email
) {
}
