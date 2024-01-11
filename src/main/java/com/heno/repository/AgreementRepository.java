package com.heno.repository;

import com.heno.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Agreement entity repository.
 */
@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Long> {

}
