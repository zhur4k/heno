package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity describing the product.
 */
@Entity
@Data
@Table(name = "product")
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
     * Field "Additional equipment"
     */
    private String additionalEquipment;
    /**
     * Field "Quantity"
     */
    private Integer quantity;
    /**
     * Field "Price"
     */
    private BigDecimal price;
    /**
     * Field "Unit"
     */
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToMany(mappedBy = "products")
    private List<Agreement> agreements = new ArrayList<>();
}
