package com.heno.repository;

import com.heno.model.SaleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Sale type entity repository.
 */
@Repository
public interface SaleTypeRepository extends JpaRepository<SaleType,Long> {
}
