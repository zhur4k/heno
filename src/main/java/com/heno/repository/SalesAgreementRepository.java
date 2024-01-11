package com.heno.repository;

import com.heno.model.SaleAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Agreement entity repository.
 */
@Repository
public interface SalesAgreementRepository extends JpaRepository<SaleAgreement,Long> {

}
