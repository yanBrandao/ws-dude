package com.dudes.wsdude.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DudeDTO extends BaseDTO {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate bornDate;
    private String CPF;
    private GenderDTO gender;

    public DudeDTO(Long id){
        this.id = id;
    }
}
