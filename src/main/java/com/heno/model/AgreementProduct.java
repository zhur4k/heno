package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity describing the product in agreement.
 */
@Entity
@Data
@Table(name = "agreements_products")
public class AgreementProduct {

    /**
     * Field "Id(AgreementProduct id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * Field "Product"
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
