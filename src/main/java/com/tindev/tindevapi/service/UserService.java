package com.tindev.tindevapi.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public UserDTO createUser(UserCreateDTO userToBeCreated) throws Exception {
        log.info("Calling the Create user method");
        userRepository.getAddressById(userToBeCreated.getAddressId());
        userRepository.getPersoInfoById(userToBeCreated.getPersoInfoId());
        User userToCreate = objectMapper.convertValue(userToBeCreated, User.class);
        return objectMapper.convertValue(userRepository.create(userToCreate), UserDTO.class);
    }

    public List<UserDTO> listUser(){
        log.info("Calling the list user method");
        return userRepository.list().stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Integer id, UserCreateDTO userUpdated) throws Exception {
        userRepository.getAddressById(userUpdated.getAddressId());
        userRepository.getPersoInfoById(userUpdated.getPersoInfoId());
        log.info("Calling the Update user method");
        User userToUpdate = objectMapper.convertValue(userUpdated, User.class);
        return objectMapper.convertValue(userRepository.update(id, userToUpdate), UserDTO.class);
    }

    public void deleteUser(Integer id) throws Exception {
        log.info("Calling the Delete user method");
        userRepository.delete(id);
    }

    public UserDTO getUserById(Integer id) throws Exception {
        log.info("Calling the get user by id method");
        return objectMapper.convertValue(userRepository.getUserById(id), UserDTO.class);
    }
}
