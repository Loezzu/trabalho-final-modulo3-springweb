package com.tindev.tindevapi.dto.like;

import lombok.Data;

@Data
public class LikeDTO {
    private Integer likeId;
    private Integer userId;
    private Integer likedUserId;

}
