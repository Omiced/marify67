package org.generation.marify.dto;

import java.time.LocalDate;

public record AlbumsRequest(String name, LocalDate releaseDate) {
}
