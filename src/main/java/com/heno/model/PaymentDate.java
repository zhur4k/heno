package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;


/**
 * Entity describing the payment date of agreement.
 */
@Entity
@Data
@Table(name = "payment_dates")
public class PaymentDate {
    /**
     * Field "ID (Payment date ID in the database)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Field "Description"
     */
    private String description;
    /**
     * Field "Prepayment percentage"
     */
    private Integer percentage;
    /**
     * Field "Amount of days"
     */
    private Integer days;
    /**
     * Field "Time unit (banking days = true, calendar days = false)"
     */
    private Boolean timeUnit;
}
