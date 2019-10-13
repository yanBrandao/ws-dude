package com.dudes.wsdude.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.dudes.wsdude.domain.Address;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DudeDTO extends BaseDTO {
    private Long id;
    private String name;
    private String lastName;
    private Date bornDate;
    private String CPF;

    @NotNull(message = "Gender is required")
    private GenderDTO gender;

    @NotNull(message = "Address is required")
    private AddressDTO address;

    public DudeDTO(Long id){
        this.id = id;
    }
}
