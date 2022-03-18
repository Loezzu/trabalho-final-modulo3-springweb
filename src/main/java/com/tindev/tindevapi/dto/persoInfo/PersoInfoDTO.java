package com.tindev.tindevapi.dto.persoInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersoInfoDTO extends PersoInfoCreateDTO{

    @ApiModelProperty(value = "Id of the user", example = "1")
    private Integer idPersoInfo;
}
