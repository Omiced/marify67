package org.generation.marify.dto;
/*
Los records son estructuras que nos brindan getter de las propiedades
que estan dentro de los parentesis, en este caso los getter se llaman
igual que la propiedad
Los records son inmutables y por eso no brindan setters
 */
public record SongsRequest(Long idAlbum, String name,
                           Integer duration) {
}
