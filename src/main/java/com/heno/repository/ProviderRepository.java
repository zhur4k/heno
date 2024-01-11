package com.heno.repository;

import com.heno.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provider entity repository.
 */
@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {

}
