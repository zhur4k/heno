package com.heno.repository;

import com.heno.model.AgreementProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product entity repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<AgreementProduct,Long> {

}
