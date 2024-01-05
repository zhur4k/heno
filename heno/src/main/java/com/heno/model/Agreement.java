package com.heno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

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
    private User employee;
    /**
     * Field "Type of the sale"
     */
    @ManyToOne
    private SaleType saleType;
    /**
     * Field "Type of the sale"
     */
    @ManyToOne
    private Buyer buyer;//6

    public Agreement() {

    }
}
