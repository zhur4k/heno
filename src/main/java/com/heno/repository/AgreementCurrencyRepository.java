package com.heno.repository;

import com.heno.model.AgreementCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Currency entity repository.
 */
@Repository
public interface AgreementCurrencyRepository extends JpaRepository<AgreementCurrency,Long> {

    List<AgreementCurrency> findAllByViewTrue();
}
