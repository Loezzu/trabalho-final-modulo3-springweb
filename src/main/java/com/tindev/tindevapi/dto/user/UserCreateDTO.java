package com.tindev.tindevapi.dto.user;

import com.tindev.tindevapi.entities.Address;
import com.tindev.tindevapi.entities.PersoInfo;
import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDTO{
    @NotNull
    private Integer PersoInfoId;
    @NotNull
    private Integer AddressId;

    @NotNull
    @NotEmpty(message = "Username could not be empty")
    private String username;

    @NotNull
    @NotEmpty(message = "Password could not be empty")
    private String password;

    @NotNull
    private ProgLangs progLangs;
    @NotNull
    private Gender gender;
    @NotNull
    private Pref pref;
}