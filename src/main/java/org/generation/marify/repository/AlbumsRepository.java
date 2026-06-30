package org.generation.marify.repository;

import org.generation.marify.model.Albums;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumsRepository extends JpaRepository<Albums, Long> {
}
