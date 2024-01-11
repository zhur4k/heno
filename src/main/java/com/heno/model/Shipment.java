package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity describing the shipment.
 */
@Entity
@Data
@Table(name = "shipment")
public class Shipment {

    /**
     * Field "Id(Shipment id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    /**
     * Field "Shipping type"
     */
    private String shippingType;
    /**
     * Field "Delivery place"
     */
    private String deliveryPlace;
}
