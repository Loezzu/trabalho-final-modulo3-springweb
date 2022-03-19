package com.tindev.tindevapi.controller.like;

import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.service.LikeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/like")
@Api(value = "4 - Like API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"4 - Like API"}, description = "like Controls")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserDTO>> listLike(@PathVariable("userId")Integer id) throws Exception {
        return ResponseEntity.ok(likeService.listLikes(id));
    }

}
