package com.tindev.tindevapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DadosPessoais {

    private Integer idPersoInfo;
    private String realName;
    private Integer age;
    private String email;
}
