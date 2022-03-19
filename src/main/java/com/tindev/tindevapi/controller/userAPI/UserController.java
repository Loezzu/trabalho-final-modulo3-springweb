package com.tindev.tindevapi.controller.userAPI;

import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@Api(value = "User API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"User API"}, description = "User Controls")
public class UserController implements UserAPI{

    @Autowired
    private UserService userService;

    @GetMapping //localhost:8080/address
    public ResponseEntity<List<UserDTO>> listUser(){
        return ResponseEntity.ok(userService.listUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getByUserId(@PathVariable("userId") Integer id) throws Exception {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@Valid @RequestBody UserCreateDTO userCreateDTO) throws Exception {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updatedUser(@PathVariable("userId") Integer id,
                                                    @Valid @RequestBody UserCreateDTO userCreateDTO) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, userCreateDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted!");
    }

    @GetMapping("/available/{userId}")
    public ResponseEntity<List<UserDTO>> listAvailable(@PathVariable("userId") Integer id) throws Exception {
        return ResponseEntity.ok(userService.listAvailable(id));
    }

}
