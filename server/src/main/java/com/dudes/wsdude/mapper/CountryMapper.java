package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.dto.CountryDTO;
import org.springframework.data.domain.Page;

public class CountryMapper implements GenericMapper<Country, CountryDTO> {
    public Country convertToEntity(CountryDTO dto){
        Country entity = new Country();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setInitials(dto.getInitials());

        return entity;
    }

    public CountryDTO convertToDTO(Country entity){

        return CountryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .initials(entity.getInitials())
                .build();
    }

    public Page<CountryDTO> convertToPageDTO(Page<Country> countryPage){
        return countryPage.map(this::convertToDTO);
    }

}