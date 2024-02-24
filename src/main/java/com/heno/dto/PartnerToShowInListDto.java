package com.heno.dto;

public record PartnerToShowInListDto(
        /**
         * Field "Id(Partner id in date base)"
         */
        Long id,
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
