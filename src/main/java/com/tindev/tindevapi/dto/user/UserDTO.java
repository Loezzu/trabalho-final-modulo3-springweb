package com.tindev.tindevapi.dto.user;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserDTO extends UserCreateDTO {
    private Integer userId;
}
