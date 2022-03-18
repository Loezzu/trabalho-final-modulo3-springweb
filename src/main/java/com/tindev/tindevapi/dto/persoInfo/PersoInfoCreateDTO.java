package com.tindev.tindevapi.dto.persoInfo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersoInfoCreateDTO {

    @ApiModelProperty(value = "The real name of the person", example = "João Silva", required = true)
    @NotNull
    @NotEmpty
    private String realName;

    @ApiModelProperty(value = "The age of the person", example = "25", required = true)
    @NotNull
    private Integer age;

    @ApiModelProperty(value = "The email of the person", example = "joaozinho@mail.com", required = true)
    @NotNull
    @NotEmpty
    @Email
    private String email;
}
