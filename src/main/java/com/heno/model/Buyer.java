package com.heno.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity describing the buyer.
 */
@Entity
@Data
@Table(name = "buyers")
public class Buyer {

    /**
     * Field "Id(Buyer id in date base)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Name"
     */
    private String name;
    /**
     * Field "Address"
     */
    private String address;
    /**
     * Field "UNP"
     */
    private Integer UNP;
    /**
     * Field "Checking account"
     */
    private String checkingAccount;
    /**
     * Field "Phone Number"
     */
    private String phoneNumber;
    /**
     * Field "Email"
     */
    private String email;
}
