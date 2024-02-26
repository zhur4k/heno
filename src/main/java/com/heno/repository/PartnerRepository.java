package com.heno.repository;

import com.heno.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Partner entity repository.
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {

}
