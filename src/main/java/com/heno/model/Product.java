package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity describing the product.
 */
@Entity
@Data
@Table(name = "products")
public class Product {

    /**
     * Field "Id(Product id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Name"
     */
    private String name;
    /**
     * Field "Unit"
     */
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
