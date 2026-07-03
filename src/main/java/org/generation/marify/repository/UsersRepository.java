package org.generation.marify.repository;

import org.generation.marify.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    //tener una propiedad que se llame como lo que buscamos
    Optional<Users> findByEmail(String email);
}
