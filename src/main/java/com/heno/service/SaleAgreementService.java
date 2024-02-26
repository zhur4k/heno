package com.heno.service;

import com.heno.dto.AgreementToShowInListDto;
import com.heno.dto.SaleAgreementAddDto;
import com.heno.dto.SaleAgreementEditDto;
import com.heno.dto.SaleAgreementGetDto;
import com.heno.dto.mapper.AgreementToShowInListDtoMapper;
import com.heno.dto.mapper.SaleAgreementAddDtoMapper;
import com.heno.dto.mapper.SaleAgreementEditDtoMapper;
import com.heno.dto.mapper.SaleAgreementGetDtoMapper;
import com.heno.model.Agreement;
import com.heno.model.User;
import com.heno.repository.AgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing sale agreements.
 */
@Service
public class SaleAgreementService {

    private final AgreementRepository agreementRepository;
    private final SaleAgreementAddDtoMapper saleAgreementAddDtoMapper;
    private final SaleAgreementEditDtoMapper saleAgreementEditDtoMapper;
    private final SaleAgreementGetDtoMapper saleAgreementGetDtoMapper;
    private final AgreementToShowInListDtoMapper agreementToShowInListDtoMapper;

    /**
     * Constructs a SaleAgreementService with the specified dependencies.
     *
     * @param agreementRepository            The repository for managing agreements.
     * @param saleAgreementAddDtoMapper      Mapper for converting SaleAgreementAddDto to Agreement.
     * @param saleAgreementEditDtoMapper     Mapper for converting SaleAgreementEditDto to Agreement.
     * @param saleAgreementGetDtoMapper      Mapper for converting Agreement to SaleAgreementGetDto.
     * @param agreementToShowInListDtoMapper Mapper for converting Agreement to AgreementToShowInListDto.
     */
    public SaleAgreementService(
            AgreementRepository agreementRepository,
            SaleAgreementAddDtoMapper saleAgreementAddDtoMapper,
            SaleAgreementEditDtoMapper saleAgreementEditDtoMapper,
            SaleAgreementGetDtoMapper saleAgreementGetDtoMapper, AgreementToShowInListDtoMapper agreementToShowInListDtoMapper) {
        this.agreementRepository = agreementRepository;
        this.saleAgreementAddDtoMapper = saleAgreementAddDtoMapper;
        this.saleAgreementEditDtoMapper = saleAgreementEditDtoMapper;
        this.saleAgreementGetDtoMapper = saleAgreementGetDtoMapper;
        this.agreementToShowInListDtoMapper = agreementToShowInListDtoMapper;
    }

    /**
     * Retrieves all AgreementToShowInListDto associated with the specified employee.
     *
     * @param employee The employee for whom to retrieve sale agreements.
     * @return A list of AgreementToShowInListDto associated with the specified employee.
     */
    public List<AgreementToShowInListDto> findAll(User employee) {
        return agreementRepository.findAllByEmployeeAndTypeOfAgreement(employee, "sale")
                .stream()
                .map(agreementToShowInListDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new sale agreement to the system.
     *
     * @param saleAgreementAddDto The DTO containing information for creating the sale agreement.
     * @param employee            The employee associated with the sale agreement.
     */
    public void addAgreement(SaleAgreementAddDto saleAgreementAddDto, User employee) {
        Agreement agreement = saleAgreementAddDtoMapper.apply(saleAgreementAddDto);
        agreement.setEmployee(employee);
        agreementRepository.save(agreement);
    }

    /**
     * Edits an existing sale agreement in the system.
     *
     * @param saleAgreementEditDto The DTO containing information for editing the sale agreement.
     * @param employee             The employee associated with the sale agreement.
     */
    public void editAgreement(SaleAgreementEditDto saleAgreementEditDto, User employee) {
        Agreement agreement = saleAgreementEditDtoMapper.apply(saleAgreementEditDto);
        agreement.setEmployee(employee);
        agreementRepository.save(agreement);
    }
    /**
     * Adds a new sale agreement to the system.
     *
     * @param id                  Id of the sale agreement.
     * @param employee            The employee associated with the sale agreement.
     */
    public SaleAgreementGetDto getAgreement(Long id, User employee) {
        return saleAgreementGetDtoMapper.apply(
                agreementRepository.findByIdAndTypeOfAgreementAndEmployee(id,"sale",employee));

    }
}

