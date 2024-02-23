package com.heno.service;

import com.heno.dto.*;
import com.heno.dto.mapper.*;
import com.heno.model.Agreement;
import com.heno.model.User;
import com.heno.repository.AgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing supply agreements.
 */
@Service
public class SupplyAgreementService {

    private final AgreementRepository agreementRepository;
    private final SupplyAgreementAddDtoMapper supplyAgreementAddDtoMapper;
    private final SupplyAgreementEditDtoMapper supplyAgreementEditDtoMapper;
    private final AgreementToShowInListDtoMapper agreementToShowInListDtoMapper;

    /**
     * Constructs a SupplyAgreementService with the specified dependencies.
     *
     * @param agreementRepository            The repository for managing agreements.
     * @param supplyAgreementAddDtoMapper      Mapper for converting SupplyAgreementAddDto to Agreement.
     * @param supplyAgreementEditDtoMapper     Mapper for converting SupplyAgreementEditDto to Agreement.
     * @param agreementToShowInListDtoMapper Mapper for converting Agreement to AgreementToShowInListDto.
     */
    public SupplyAgreementService(AgreementRepository agreementRepository, SupplyAgreementAddDtoMapper supplyAgreementAddDtoMapper, SupplyAgreementEditDtoMapper supplyAgreementEditDtoMapper, AgreementToShowInListDtoMapper agreementToShowInListDtoMapper) {
        this.agreementRepository = agreementRepository;
        this.supplyAgreementAddDtoMapper = supplyAgreementAddDtoMapper;
        this.supplyAgreementEditDtoMapper = supplyAgreementEditDtoMapper;
        this.agreementToShowInListDtoMapper = agreementToShowInListDtoMapper;
    }

    /**
     * Retrieves all AgreementToShowInListDto associated with the specified employee.
     *
     * @param employee The employee for whom to retrieve sale agreements.
     * @return A list of AgreementToShowInListDto associated with the specified employee.
     */
    public List<AgreementToShowInListDto> findAll(User employee) {
        return agreementRepository.findAllByEmployeeAndTypeOfAgreement(employee, "supply")
                .stream()
                .map(agreementToShowInListDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new supply agreement to the system.
     *
     * @param supplyAgreementAddDto The DTO containing information for creating the supply agreement.
     * @param employee            The employee associated with the supply agreement.
     */
    public void addAgreement(SupplyAgreementAddDto supplyAgreementAddDto, User employee) {
        Agreement agreement = supplyAgreementAddDtoMapper.apply(supplyAgreementAddDto);
        agreement.setEmployee(employee);
        agreementRepository.save(agreement);
    }

    /**
     * Edits an existing supply agreement in the system.
     *
     * @param supplyAgreementEditDto The DTO containing information for editing the supply agreement.
     * @param employee             The employee associated with the supply agreement.
     */
    public void editAgreement(SupplyAgreementEditDto supplyAgreementEditDto, User employee) {
        Agreement agreement = supplyAgreementEditDtoMapper.apply(supplyAgreementEditDto);
        agreement.setEmployee(employee);
        agreementRepository.save(agreement);
    }
//    /**
//     * Retrieves all sale agreements.
//     *
//     * @return A list of sale agreements.
//     */
//    public List<Agreement> findAll() {
//        return agreementRepository.findAllByTypeOfAgreement("sale");
//    }
}

