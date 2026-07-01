package org.generation.marify.service;


import lombok.AllArgsConstructor;
import org.generation.marify.dto.SongsRequest;
import org.generation.marify.model.Albums;
import org.generation.marify.model.Songs;
import org.generation.marify.repository.AlbumsRepository;
import org.generation.marify.repository.SongsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongsService {
    private final SongsRepository songsRepository;
    private final AlbumsRepository albumsRepository;

    public Songs addSong(SongsRequest songRequest){
        Albums album = albumsRepository.findById(songRequest.idAlbum()).orElseThrow(
                () -> new IllegalArgumentException("El album no existe")
        );
        Songs newSong = new Songs();

        if(songRequest.name() != null) newSong.setName(songRequest.name());
        if(songRequest.duration() != null) newSong.setDuration(songRequest.duration());
        newSong.setAlbum(album);
        album.getSongs().add(newSong);
        songsRepository.save(newSong);
        albumsRepository.save(album);
        return newSong;
    }

    public Songs getSong(Long id){
        return songsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cancion no encontrada")
        );
    }

    /*
    Metodo de borrar cancion
    Metodo de obtenr todas las canciones
     */

    public Songs deleteSongById(Long id){
        Songs song = getSong(id);
        songsRepository.delete(song);
        return song;
    }

    public List<Songs> getAllSongs(){
        return songsRepository.findAll();

    }

}
