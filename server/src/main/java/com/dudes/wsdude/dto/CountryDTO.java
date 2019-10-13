package com.dudes.wsdude.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private Long id;
    private String name;
    private String initials;

    public CountryDTO(Long id){
        this.id = id;
    }
}
