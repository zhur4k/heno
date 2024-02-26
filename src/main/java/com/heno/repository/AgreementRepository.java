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

    /**
     * Retrieves a list of agreements based on the specified employee and type of agreement.
     *
     * @param employee         The user associated with the agreements.
     * @param typeOfAgreement  The type of agreement to filter by.
     * @return                  A list of agreements that match the criteria.
     */
    List<Agreement> findAllByEmployeeAndTypeOfAgreement(User employee, String typeOfAgreement);

    /**
     * Retrieves a list of agreements based on the specified type of agreement.
     *
     * @param typeOfAgreement  The type of agreement to filter by.
     * @return                  A list of agreements that match the specified type.
     */
    List<Agreement> findAllByTypeOfAgreement(String typeOfAgreement);
    /**
     * Retrieves an agreement based on the id, specified employee and type of agreement.
     *
     * @param id               Id of agreement to filter by.
     * @param typeOfAgreement  The type of agreement to filter by.
     * @param employee         The user associated with the agreements.
     * @return                  An agreement.
     */
    Agreement findByIdAndTypeOfAgreementAndEmployee(Long id, String typeOfAgreement, User employee);
}
