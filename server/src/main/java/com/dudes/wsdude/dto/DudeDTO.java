package com.dudes.wsdude.dto;

import java.util.Date;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DudeDTO {
    private Long id;
    private String name;
    private String lastName;
    private Date bornDate;
    private String CPF;

    @NotNull(message = "Gender is required")
    private GenderDTO gender;

    @NotNull(message = "Address is required")
    private AddressDTO address;
}
