package com.heno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity describing the agreement.
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "agreements")
public class Agreement{

    /**
     * Field "Id(Agreement id in date base)"
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
    private LocalDateTime dateOfAgreement;
    /**
     * Field "Date of registration  of agreement "
     */
    private LocalDateTime dateOfRegistrationAgreement;
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
    @ManyToMany
    @JoinTable(
            name = "agreement_product",
            joinColumns = @JoinColumn(name = "agreement_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Agreement() {

    }
}
