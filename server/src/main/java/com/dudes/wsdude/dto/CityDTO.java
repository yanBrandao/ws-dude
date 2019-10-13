package com.dudes.wsdude.dto;

import com.dudes.wsdude.domain.State;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private Long id;
    private String name;
    private StateDTO state;

    public CityDTO(Long id){
        this.id = id;
    }
}
