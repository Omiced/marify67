package org.generation.marify.repository;

import org.generation.marify.model.Artists;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository pide en el operador diamante <>
 *  Primero la entidad sobre la que va a trabajar en este caso Artists
 *  Segundo el tipo de dato del id de esta entidad en este caso Long
 */
public interface ArtistsRepository extends JpaRepository<Artists, Long> {
}

