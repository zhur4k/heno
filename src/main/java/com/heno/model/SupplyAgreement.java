package com.heno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity describing the supply agreement.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "supply_agreements")
public class SupplyAgreement {

    /**
     * Field "Id(Supply agreement id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Number of agreement"
     */
    private Integer number;
    /**
     * Field "Provider"
     */
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
    /**
     * Field "Date of manufacturing"
     */
    private LocalDate dateOfManufacturing;
    /**
     * Field "Payment date of agreement"
     */
    @OneToMany
    @JoinColumn(name = "payment_date_id")
    private List<PaymentDate> paymentDate;
    /**
     * Field "Date of registration  of agreement "
     */
    private LocalDate dateOfRegistrationAgreement;
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
     * Field "The employee who made the supplies"
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User employee;
    /**
     * Field "Shipment info"
     */
    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    public SupplyAgreement() {

    }
}
