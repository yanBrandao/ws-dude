package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.dto.GenderDTO;
import org.springframework.data.domain.Page;

public class GenderMapper implements GenericMapper<Gender, GenderDTO> {
    public Gender convertToEntity(GenderDTO dto) {
        Gender entity = new Gender();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }

    public GenderDTO convertToDTO(Gender entity) {
        GenderDTO dto = new GenderDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    public Page<GenderDTO> convertToPageDTO(Page<Gender> dudePage) {
        return dudePage.map(this::convertToDTO);
    }

}
