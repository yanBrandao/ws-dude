package com.dudes.wsdude.dto;

import com.dudes.wsdude.domain.Country;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {
    private Long id;
    private String name;
    private String initials;
    private CountryDTO country;

    public StateDTO(Long id){
        this.id = id;
    }
}
