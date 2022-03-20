package com.tindev.tindevapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Like {

    private Integer likeId;

    private Integer userId;
    private Integer likedUserId;
}
