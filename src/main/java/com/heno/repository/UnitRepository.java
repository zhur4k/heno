package com.heno.repository;

import com.heno.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Unit entity repository.
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {

}
