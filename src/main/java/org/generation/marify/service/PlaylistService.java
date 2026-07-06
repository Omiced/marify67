package org.generation.marify.service;

import lombok.AllArgsConstructor;
import org.generation.marify.dto.PlaylistRequest;
import org.generation.marify.model.Playlist;
import org.generation.marify.model.Users;
import org.generation.marify.repository.PlaylistRepository;
import org.generation.marify.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UsersRepository usersRepository;

    public List<Playlist> getAllPlaylists(){
        return playlistRepository.findAll();
    }

    public Playlist getSinglePlaylist(Long id){
        return playlistRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("No se encontro la playlist")
        );
    }

    public Playlist createPlaylist(PlaylistRequest playlistRequest){
        Users user = usersRepository.findById(playlistRequest.userId()).orElseThrow(
                () -> new IllegalArgumentException("Usuario no encontrado")
        );

        Playlist playlistDb = new Playlist();
        if(playlistRequest.playlistName() != null) playlistDb.setName(playlistRequest.playlistName());
        playlistDb.setUser(user);
        user.getPlaylists().add(playlistDb);
        playlistRepository.save(playlistDb);
        usersRepository.save(user);
        return playlistDb;
    }
}
