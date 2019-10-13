package com.dudes.wsdude.dto;

import com.dudes.wsdude.domain.City;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String street;
    private String neighborhood;
    private String number;
    private CityDTO city;

    public AddressDTO(Long id){
        this.id = id;
    }
}
