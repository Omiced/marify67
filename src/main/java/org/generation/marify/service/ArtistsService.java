package org.generation.marify.service;


import org.generation.marify.dto.AlbumsRequest;
import org.generation.marify.model.Albums;
import org.generation.marify.model.Artists;
import org.generation.marify.repository.AlbumsRepository;
import org.generation.marify.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//completamente opcional desde spring 3
@Service
public class ArtistsService {
    /**
     * modos de hacer inyección de dependencia
     * 1.  por propiedad esta es la menos comun
     * 2. en metodo
     * 3. por constructor !esta es la que vamos a ocupar en spring boot
     */
    private final ArtistsRepository artistsRepository;
    private final AlbumsRepository albumsRepository;
    @Autowired //totalmente opcional, aqui ocurre la inyeccion
    public ArtistsService(ArtistsRepository artistsRepository, AlbumsRepository albumsRepository) {
        this.artistsRepository = artistsRepository;
        this.albumsRepository =  albumsRepository;
    }

    public List<Artists> getAllArtists(){
        return artistsRepository.findAll();
    }

    public Artists getArtistById(Long id){
        return artistsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Artista no encontrado")
        );
    }

    public Artists createArtist(Artists newArtist){
        return artistsRepository.save(newArtist);
    }

    public Artists deleteArtistById(Long id){
        //Verificamos si existe y lo guardamos en la variable tmp
        Artists tmp = artistsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Artista no encontrado")
        );
        artistsRepository.delete(tmp);
        return tmp;
    }

    /**
     *El metodo .save de jparepository tiene doble proposito
     * 1. Si no existe el recurso lo crea
     * 2. Si ya existe el recurso lo actualiza
     */
    public Artists updateArtistById(Long id, Artists updatedArtist){
        Artists savedArtist = artistsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Artista no encontrado")
        );
        if(updatedArtist.getName() != null) savedArtist.setName(updatedArtist.getName());
        if(updatedArtist.getGenere() != null) savedArtist.setGenere(updatedArtist.getGenere());
        return artistsRepository.save(savedArtist);
    }

    public Artists addAlbumArtist(Long artistId, AlbumsRequest albumsRequest){
        //1. verificando si existe un artista con base en el id
        Artists savedArtist = artistsRepository.findById(artistId).orElseThrow(
                () -> new IllegalArgumentException("Artista no encontrado")
        );
        //2. Si existe el artista creear un album vacio
        Albums album = new Albums();
        /*3. verificar la informacion que viene en el albumrequest
        *  si viene informacion se la asiganamos al album
        * */
        if(albumsRequest.name() != null) album.setName(albumsRequest.name());
        if(albumsRequest.releaseDate() != null) album.setReleaseDate(albumsRequest.releaseDate());
        //4. asignando el artista al que pertene el albul
        album.setArtist(savedArtist);
        //5. guardando el album
        albumsRepository.save(album);
        //6. actualizando la lista de albums del artista
        savedArtist.getAlbums().add(album);
        //7. actualizando el artista con sus albums en la bd
        return artistsRepository.save(savedArtist);
    }

}
