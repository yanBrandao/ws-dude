package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.dto.AddressDTO;
import org.springframework.data.domain.Page;

public class AddressMapper implements GenericMapper<Address, AddressDTO> {
    public Address convertToEntity(AddressDTO dto){
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setStreet(dto.getStreet());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setNumber(dto.getNumber());
        if(dto.getCity() != null)
            entity.setCity(new CityMapper().convertToEntity(dto.getCity()));

        return entity;
    }

    public AddressDTO convertToDTO(Address entity){
        AddressDTO dto = AddressDTO.builder()
                .id(entity.getId())
                .neighborhood(entity.getNeighborhood())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .build();
        if(entity.getCity() != null)
            dto.setCity(new CityMapper().convertToDTO(entity.getCity()));

        return dto;
    }

    public Page<AddressDTO> convertToPageDTO(Page<Address> addressPage){
        return addressPage.map(this::convertToDTO);
    }

}
