package com.heno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity describing the sale agreement.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "sale_agreements")
public class Agreement {

    /**
     * Field "Id(Sales agreement id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Number of agreement"
     */
    private Integer number;

    /**
     * Field "Date of agreement"
     */
    private LocalDate dateOfAgreement;
    /**
     * Field "Date of registration  of agreement "
     */
    private LocalDate dateOfRegistrationAgreement;
    /**
     * Field "Date of supplies"
     */
    private LocalDate dateOfSupplies;
    /**
     * Field "The employee who made the sale"
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User employee;
    /**
     * Field "Type of the sale"
     */
    @ManyToOne
    @JoinColumn(name = "sale_type_id")
    private SaleType saleType;
    /**
     * Field "Info about buyer"
     */
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    /**
     * Field "Products in agreement"
     */
    @OneToMany
    private List<Product> products = new ArrayList<>();
    /**
     * Field "Currency of agreement"
     */
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private AgreementCurrency currency;
    /**
     * Field "Payment date of agreement"
     */
    @OneToMany
    @JoinColumn(name = "payment_date_id")
    private List<PaymentDate> paymentDate;
    /**
     * Field "Shipment info"
     */
    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    public Agreement() {

    }
}
