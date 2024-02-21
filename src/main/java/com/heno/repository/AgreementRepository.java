package com.heno.repository;

import com.heno.model.Agreement;
import com.heno.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Agreement entity repository.
 */
@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Long> {

    List<Agreement> findAllByEmployeeAndTypeOfAgreement(User employee,String typeOfAgreement);
}
