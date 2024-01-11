package com.heno.repository;

import com.heno.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Buyer entity repository.
 */
@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long> {

}
