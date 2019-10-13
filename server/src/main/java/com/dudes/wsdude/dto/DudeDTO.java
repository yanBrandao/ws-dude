package com.dudes.wsdude.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dudes.wsdude.domain.Address;
import lombok.*;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Gender is required")
    private GenderDTO gender;

    @NotBlank(message = "Address is required")
    private AddressDTO address;

    public DudeDTO(Long id){
        this.id = id;
    }
}
