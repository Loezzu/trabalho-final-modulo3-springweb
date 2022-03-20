package com.tindev.tindevapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Match {

    private Integer matchId;
    private Integer matchedUserFirst;
    private Integer matchedUserSecond;
}
