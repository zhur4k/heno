package com.heno.dto;

import com.heno.model.*;

import java.time.LocalDate;
import java.util.List;

public record SupplyAgreementAddDto(
        /**
         * Field "Number of agreement"
         */
        Integer number,

        /**
         * Field "Date of agreement"
         */
        LocalDate dateOfAgreement,
        /**
         * Field "Date of registration  of agreement "
         */
        LocalDate dateOfRegistrationAgreement,
        /**
         * Field "Date of supplies"
         */
        LocalDate dateOfSupplies,
        /**
         * Field "Type of the sale"
         */
        SaleType saleType,
        /**
         * Field "Info about partner"
         */

        Partner partner,
        /**
         * Field "Products in agreement"
         */
        List<AgreementProduct> products,
        /**
         * Field "Currency of agreement"
         */
        AgreementCurrency currency,
        /**
         * Field "Payment date of agreement"
         */
        List<PaymentDate> paymentDate,
        /**
         * Field "Shipment info"
         */
         Shipment shipment,
        /**
         * Field "The employee who made the sale"
         */
        User employee
) {
}
