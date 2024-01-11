package com.heno.repository;

import com.heno.model.PaymentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Payment date entity repository.
 */
@Repository
public interface PaymentDateRepository extends JpaRepository<PaymentDate,Long> {

}
