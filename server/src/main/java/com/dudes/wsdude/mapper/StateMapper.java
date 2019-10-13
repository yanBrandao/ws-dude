package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.dto.StateDTO;
import org.springframework.data.domain.Page;

public class StateMapper implements GenericMapper<State, StateDTO> {
    public State convertToEntity(StateDTO dto) {
        State entity = new State();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setInitials(dto.getInitials());
        if(dto.getCountry() != null)
            entity.setCountry(new CountryMapper().convertToEntity(dto.getCountry()));

        return entity;
    }

    public StateDTO convertToDTO(State entity) {
        StateDTO dto = StateDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .initials(entity.getInitials())
                .build();
        if(entity.getCountry() != null)
            dto.setCountry(new CountryMapper().convertToDTO(entity.getCountry()));

        return dto;
    }

    public Page<StateDTO> convertToPageDTO(Page<State> statePage) {
        return statePage.map(this::convertToDTO);
    }
}
