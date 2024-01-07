package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity describing the units.
 */
@Entity
@Data
@Table(name = "units")
public class Unit {

    /**
     * Field "Id(Unit id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Name"
     */
    private String name;
}
