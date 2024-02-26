package com.heno.service;

import com.heno.dto.AgreementToShowInListDto;
import com.heno.dto.mapper.AgreementToShowInListDtoMapper;
import com.heno.repository.AgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing agreements.
 */
@Service
public class AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementToShowInListDtoMapper agreementToShowInListDtoMapper;

    /**
     * Constructs a SaleAgreementService with the specified dependencies.
     *
     * @param agreementRepository            The repository for managing agreements.
     * @param agreementToShowInListDtoMapper The dto of agreement to get agreements in list.
     */
    public AgreementService(AgreementRepository agreementRepository, AgreementToShowInListDtoMapper agreementToShowInListDtoMapper) {
        this.agreementRepository = agreementRepository;
        this.agreementToShowInListDtoMapper = agreementToShowInListDtoMapper;
    }

    /**
     * Retrieves all AgreementToShowInListDto.
     *
     * @return A list of AgreementToShowInListDto.
     */
    public List<AgreementToShowInListDto> findAll() {
        return agreementRepository.findAll()
                .stream()
                .map(agreementToShowInListDtoMapper)
                .collect(Collectors.toList());
    }
}

