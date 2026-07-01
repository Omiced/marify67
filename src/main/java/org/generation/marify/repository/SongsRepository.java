package org.generation.marify.repository;

import org.generation.marify.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongsRepository extends JpaRepository<Songs, Long> {
}
