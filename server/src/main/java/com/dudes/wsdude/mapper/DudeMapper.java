package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.dto.DudeDTO;
import org.springframework.data.domain.Page;

public class DudeMapper implements GenericMapper<Dude, DudeDTO> {
    public Dude convertToEntity(DudeDTO dto){
        Dude entity = new Dude();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCPF(dto.getCPF());
        entity.setLastName(dto.getLastName());
        entity.setBornDate(dto.getBornDate());

        return entity;
    }

    public DudeDTO convertToDTO(Dude entity){
        DudeDTO dto = new DudeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCPF(entity.getCPF());
        dto.setLastName(entity.getLastName());
        dto.setBornDate(entity.getBornDate());

        return dto;
    }

    public Page<DudeDTO> convertToPageDTO(Page<Dude> dudePage){
        return dudePage.map(this::convertToDTO);
    }

}
