package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.like.LikeCreateDTO;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.Like;
import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    public List<LikeDTO> listLikesById(Integer id) throws Exception {

       UserDTO user = userService.getUserById(id);

//       User userEntity = objectMapper.convertValue(user, User.class);

       List<Like> likes = likeRepository.listLikesByUserId(user);

       return objectMapper.convertValue(likes, objectMapper.getTypeFactory().constructCollectionType(List.class, LikeDTO.class));
    }

    public LikeDTO darLike(LikeCreateDTO likeCreate, Integer id) throws Exception {
      Like like = objectMapper.convertValue(likeCreate, Like.class);
      return objectMapper.convertValue(likeRepository.darLike(like, id), LikeDTO.class);

    }












//    public List<UserDTO> listLikes(Integer id) throws Exception {
//
//        //retornar lista de likes
//        User user = objectMapper.convertValue(userService.getUserById(id), User.class);
//        List<User> likes = likeRepository.listLikesByUser(user);
//
//        //converter lista de likes para lista de userDTO
//        List<UserDTO> userDTOList = likes.stream().map(user1 -> objectMapper.convertValue(user1, UserDTO.class)).collect(Collectors.toList());
//
//        return userDTOList;
//
////        User user = objectMapper.convertValue(userService.getUserById(id), User.class);
////        return likeRepository.listLikesByUser(user).stream().map(user1 -> objectMapper.convertValue(user1, UserDTO.class)).collect(Collectors.toList());
//    }
//
//    public UserDTO like(Integer id, Integer idUser) throws Exception {
//
//        UserDTO userDTO1 = objectMapper.convertValue(userService.getUserById(id), UserDTO.class);
//        UserDTO userDTO2 = objectMapper.convertValue(userService.getUserById(idUser), UserDTO.class);
//
//        User user = objectMapper.convertValue(userDTO1, User.class);
//        User user1 = objectMapper.convertValue(userDTO2, User.class);
//
//        UserDTO userDTO = objectMapper.convertValue(likeRepository.like(user, user1), UserDTO.class);
//
//
//        return userDTO;
//    }
}
