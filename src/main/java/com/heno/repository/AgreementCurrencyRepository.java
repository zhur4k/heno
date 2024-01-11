package com.heno.repository;

import com.heno.model.AgreementCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Currency entity repository.
 */
@Repository
public interface AgreementCurrencyRepository extends JpaRepository<AgreementCurrency,Long> {

}
