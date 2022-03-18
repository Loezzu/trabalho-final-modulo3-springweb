package com.tindev.tindevapi.dto.persoInfo;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersoInfoCreateDTO {

    @NotNull
    @NotEmpty
    private String realName;

    @NotNull
    private Integer age;

    @NotNull
    @NotEmpty
    @Email
    private String email;
}
