package org.generation.marify.service;


import lombok.AllArgsConstructor;
import org.generation.marify.dto.UserResponse;
import org.generation.marify.model.Users;
import org.generation.marify.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    private UserResponse createResponse(Users user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public List<UserResponse> getAllUsers(){
        return usersRepository.findAll().stream().map( user -> createResponse(user) ).toList();
    }

    public UserResponse getUserById(Long id){
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado")
        );
        return createResponse(user);
    }

    public UserResponse createUser(Users user){
        //Paso uno obtener y cifrar el password
        String encriptedPassword = passwordEncoder.encode(user.getPassword());
        //paso 2 fijar el password cifrado en el usuario
        user.setPassword(encriptedPassword);
        //paso 3 guardar el usuario
        usersRepository.save(user);
        return createResponse(user);
    }
    //crear metodo de borrado y actualizacion

    public UserResponse updateUserById(Long id , Users userUpdates){
        Users ogUser = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado")
        );

        if(userUpdates.getName() != null) ogUser.setName(userUpdates.getName());
        if(userUpdates.getLastName() != null) ogUser.setLastName(userUpdates.getLastName());
        if(userUpdates.getEmail() != null) ogUser.setEmail(userUpdates.getEmail());
        if(userUpdates.getPassword() != null) ogUser.setPassword(passwordEncoder.encode(userUpdates.getPassword()));
        usersRepository.save(ogUser);
        return createResponse(ogUser);
    }

    public UserResponse deleteUserById(Long id){
        Users user = usersRepository.findById(id)
                .orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado")
        );
        usersRepository.delete(user);
        return createResponse(user);
    }

    public Boolean loginUser(Users user){
        Users savedUser = usersRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Credenciales incorrectas")
        );
        System.out.println(passwordEncoder.matches(user.getPassword(), savedUser.getPassword()));
        return passwordEncoder.matches(user.getPassword(), savedUser.getPassword());
    }
}
