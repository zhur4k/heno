package com.heno.service;

import com.heno.dto.CurrencyToShowInListDto;
import com.heno.dto.mapper.CurrencyToShowInListDtoMapper;
import com.heno.model.AgreementCurrency;
import com.heno.repository.AgreementCurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private final AgreementCurrencyRepository currencyRepository;
    private final CurrencyToShowInListDtoMapper currencyToShowInListDtoMapper;

    /**
     * Constructs a CurrencyService with the specified dependencies.
     *
     * @param currencyRepository            The repository for managing Currencies.
     * @param currencyToShowInListDtoMapper Mapper for converting Currency to CurrencyToShowInListDto.
     */
    public CurrencyService(AgreementCurrencyRepository currencyRepository, CurrencyToShowInListDtoMapper currencyToShowInListDtoMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyToShowInListDtoMapper = currencyToShowInListDtoMapper;
    }

    /**
     * Retrieves all view CurrencyDto.
     *
     * @return A list of view CurrencyDto.
     */
    public List<CurrencyToShowInListDto> findAllView() {
        return currencyRepository.findAllByViewTrue()
                .stream()
                .map(currencyToShowInListDtoMapper)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all CurrencyDto.
     *
     * @return A list of CurrencyDto.
     */
    public List<CurrencyToShowInListDto> findAll() {
        return currencyRepository.findAll()
                .stream()
                .map(currencyToShowInListDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Adds a Currency to the view list.
     *
     * @param id Id of the Currency.
     */
    public void addCurrency(Long id) {
        AgreementCurrency currency = currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found with ID: " + id));
        currency.setView(true);
        currencyRepository.save(currency);
    }

    /**
     * Delete a Currency from the view list.
     *
     * @param id Id of the Currency.
     */
    public void deleteCurrency(Long id) {
        AgreementCurrency currency = currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found with ID: " + id));
        currency.setView(false);
        currencyRepository.save(currency);
    }
}
