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
        if(dto.getGender() != null)
            entity.setGender(new GenderMapper().convertToEntity(dto.getGender()));

        if(dto.getAddress() != null)
            entity.setAddress(new AddressMapper().convertToEntity(dto.getAddress()));

        return entity;
    }

    public DudeDTO convertToDTO(Dude entity){
        DudeDTO dto = new DudeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCPF(entity.getCPF());
        dto.setLastName(entity.getLastName());
        dto.setBornDate(entity.getBornDate());
        if(entity.getGender() != null)
            dto.setGender(new GenderMapper().convertToDTO(entity.getGender()));

        if(entity.getAddress() != null)
            dto.setAddress(new AddressMapper().convertToDTO(entity.getAddress()));

        return dto;
    }

    public Page<DudeDTO> convertToPageDTO(Page<Dude> dudePage){
        return dudePage.map(this::convertToDTO);
    }

}
