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
     * Field "Price"
     */
    private BigDecimal price;

    /**
     * Field "Quantity"
     */
    private Integer quantity;
    /**
     * Field "Additional equipment"
     */
    private String additionalEquipment;
    /**
     * Field "Delivery time"
     */
    private LocalDate deliveryTime;
    /**
     * Field "Unit"
     */
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
    /**
     * Field "Provider the products"
     */
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
