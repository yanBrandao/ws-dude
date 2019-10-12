package com.dudes.wsdude.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenderDTO {
    private Long id;
    private String name;

    public GenderDTO(Long id){
        this.id = id;
    }
}
