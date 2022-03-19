package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    public List<UserDTO> listLikes(Integer id) throws Exception {
        User user = objectMapper.convertValue(userService.getUserById(id), User.class);
        return likeRepository.listLikesByUser(user).stream()
                .map(user1 -> objectMapper.convertValue(user1, UserDTO.class)).collect(Collectors.toList());
    }

}
