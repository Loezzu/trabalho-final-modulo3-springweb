package com.tindev.tindevapi.dto.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserDTO extends UserCreateDTO {

    @ApiModelProperty(value = "The user's ID", example = "1")
    private Integer userId;

}
