package org.generation.marify.controller;

import lombok.AllArgsConstructor;
import org.generation.marify.dto.UserResponse;
import org.generation.marify.model.Users;
import org.generation.marify.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public UserResponse getUserById(@PathVariable("userId") Long id){
        return usersService.getUserById(id);
    }

    @PostMapping
    public UserResponse addUser(@RequestBody Users user){
        return usersService.createUser(user);
    }

    @PutMapping(path="{userId}")
    public UserResponse updateUser(@PathVariable("userId") Long id, @RequestBody Users user){
        return usersService.updateUserById(id, user);
    }

    @PostMapping(path = "/login")//http://localhost:8080/api/users/login
    public Boolean loginUser(@RequestBody Users users){
        return usersService.loginUser(users);
    }
    @DeleteMapping(path = "{userId}")
    public UserResponse deleteUserById(@PathVariable("userId") Long id){
        return usersService.deleteUserById(id);
    }

}
