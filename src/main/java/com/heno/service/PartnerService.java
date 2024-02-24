package com.heno.service;

import com.heno.dto.PartnerAddDto;
import com.heno.dto.PartnerEditDto;
import com.heno.dto.PartnerToShowInListDto;
import com.heno.dto.mapper.PartnerAddDtoMapper;
import com.heno.dto.mapper.PartnerEditDtoMapper;
import com.heno.dto.mapper.PartnerToShowInListDtoMapper;
import com.heno.model.Partner;
import com.heno.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerAddDtoMapper partnerAddDtoMapper;
    private final PartnerEditDtoMapper partnerEditDtoMapper;
    private final PartnerToShowInListDtoMapper partnerToShowInListDtoMapper;



    /**
     * Constructs a PartnerService with the specified dependencies.
     *
     * @param partnerRepository            The repository for managing partners.
     * @param partnerAddDtoMapper          Mapper for converting PartnerAddDto to partner.
     * @param partnerEditDtoMapper         Mapper for converting PartnerEditDto to partner.
     * @param partnerToShowInListDtoMapper Mapper for converting partner to partnerToShowInListDto.
     */
    public PartnerService(PartnerRepository partnerRepository, PartnerAddDtoMapper partnerAddDtoMapper, PartnerEditDtoMapper partnerEditDtoMapper, PartnerToShowInListDtoMapper partnerToShowInListDtoMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerAddDtoMapper = partnerAddDtoMapper;
        this.partnerEditDtoMapper = partnerEditDtoMapper;
        this.partnerToShowInListDtoMapper = partnerToShowInListDtoMapper;
    }

    /**
     * Retrieves all PartnerDto.
     *
     * @return A list of PartnerDto.
     */
    public List<PartnerToShowInListDto> findAll() {
        return partnerRepository.findAll()
                .stream()
                .map(partnerToShowInListDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new partner to the system.
     *
     * @param partnerAddDto The DTO containing information for creating the partner.
     */
    public void addPartner(PartnerAddDto partnerAddDto) {
        Partner partner = partnerAddDtoMapper.apply(partnerAddDto);
        partnerRepository.save(partner);
    }

    /**
     * Edits an existing partner in the system.
     *
     * @param partnerEditDto The DTO containing information for editing the partner.
     */
    public void editPartner(PartnerEditDto partnerEditDto) {
        Partner partner = partnerEditDtoMapper.apply(partnerEditDto);
        partnerRepository.save(partner);
    }
}
