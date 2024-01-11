package com.heno.repository;

import com.heno.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Shipment entity repository.
 */
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {

}
