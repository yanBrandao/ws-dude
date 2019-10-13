package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.dto.CityDTO;
import org.springframework.data.domain.Page;

public class CityMapper implements GenericMapper<City, CityDTO> {
    public City convertToEntity(CityDTO dto){
        City entity = new City();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        if(dto.getState() != null)
            entity.setState(new StateMapper().convertToEntity(dto.getState()));

        return entity;
    }

    public CityDTO convertToDTO(City entity){
        CityDTO dto = CityDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        if(entity.getState() != null)
            dto.setState(new StateMapper().convertToDTO(entity.getState()));

        return dto;
    }

    public Page<CityDTO> convertToPageDTO(Page<City> cityPage){
        return cityPage.map(this::convertToDTO);
    }

}