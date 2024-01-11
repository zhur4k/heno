package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity describing the sale type.
 */
@Entity
@Data
@Table(name = "sale_type")
public class SaleType {

    /**
     * Field "Id(Sale type id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Name"
     */
    private String name;
}
