package org.generation.marify.controller;

import lombok.AllArgsConstructor;
import org.generation.marify.dto.UserResponse;
import org.generation.marify.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
