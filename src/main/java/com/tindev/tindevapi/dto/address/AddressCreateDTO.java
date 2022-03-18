package com.tindev.tindevapi.dto.address;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class AddressCreateDTO {

    @NotEmpty(message = "Street could not be empty")
    private String street;
    @NotEmpty(message = "Number could not be empty")
    private String number;
    @NotEmpty(message = "City could not be empty")
    private String city;
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "CEP invalido")
    private String cep;

}
