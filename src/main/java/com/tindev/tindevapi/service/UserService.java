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
        userRepository.getAddressById(userToBeCreated.getAddressId());
        userRepository.getPersoInfoById(userToBeCreated.getPersoInfoId());
        log.info("Chamou criar user");
        User userToCreate = objectMapper.convertValue(userToBeCreated, User.class);
        return objectMapper.convertValue(userRepository.create(userToCreate), UserDTO.class);
    }

    public List<UserDTO> listUser(){
        log.info("Chamou listar user");
        return userRepository.list().stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Integer id, UserCreateDTO userUpdated) throws Exception {
        userRepository.getAddressById(userUpdated.getAddressId());
        userRepository.getPersoInfoById(userUpdated.getPersoInfoId());
        log.info("Chamou atualizar user");
        User userToUpdate = objectMapper.convertValue(userUpdated, User.class);
        return objectMapper.convertValue(userRepository.update(id, userToUpdate), UserDTO.class);
    }

    public void deleteUser(Integer id) throws Exception {
        log.info("Chamou deletar user");
        userRepository.delete(id);
    }

    public UserDTO getUserById(Integer id) throws Exception {
        log.info("Chamou pegar user por id");
        return objectMapper.convertValue(userRepository.getUserById(id), UserDTO.class);
    }
}
