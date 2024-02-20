package com.heno.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity describing the currency of an agreement.
 */
@Entity
@Data
@Table(name = "agreement_currencies")
public class AgreementCurrency {
    /**
     * Field "ID (Currency ID in the database)"
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Field "Internal code in NB RB"
     */
    @JsonProperty("Cur_ID")
    private String Cur_ID;
    /**
     * Field "Date of last request"
     */
    @JsonProperty("Date")
    private LocalDate cur_date;
    /**
     * Field "Letter code"
     */
    @JsonProperty("Cur_Abbreviation")
    private String Cur_Abbreviation;

    /**
     * Field "Scale of currency"
     */
    @JsonProperty("Cur_Scale")
    private BigDecimal Cur_Scale;

    /**
     * Field "Name of currency"
     */
    @JsonProperty("Cur_Name")
    private String Cur_Name;

    /**
     * Field "Exchange rate"
     */
    @JsonProperty("Cur_OfficialRate")
    private BigDecimal Cur_OfficialRate;
}
