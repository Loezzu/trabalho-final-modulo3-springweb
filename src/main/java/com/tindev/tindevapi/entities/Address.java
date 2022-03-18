package com.tindev.tindevapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {

    private Integer idAddress;
    private String street;
    private String number;
    private String city;
    private String cep;

}
