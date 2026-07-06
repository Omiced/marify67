package org.generation.marify.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.generation.marify.model.Playlist;
import org.generation.marify.model.Songs;
import org.generation.marify.repository.PlaylistRepository;
import org.generation.marify.repository.SongsRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaylistSongService {
    private final PlaylistRepository playlistRepository;
    private final SongsRepository songsRepository;

    @Transactional
    public void addSongToPlaylist(Long idPlaylist, Long idSong){
        Playlist playlist = playlistRepository.findById(idPlaylist).orElseThrow(
                () -> new IllegalArgumentException("Playlist no encontrada")
        );

        Songs song = songsRepository.findById(idSong).orElseThrow(
                () -> new IllegalArgumentException("Cancion no encontrada")
        );

        playlist.addSong(song);
        playlistRepository.save(playlist);
    }

    @Transactional
    public void removeSongFromPlaylist(Long idPlaylist, Long idSong){
        Playlist playlist = playlistRepository.findById(idPlaylist).orElseThrow(
                () -> new IllegalArgumentException("Playlist no encontrada")
        );

        Songs song = songsRepository.findById(idSong).orElseThrow(
                () -> new IllegalArgumentException("Cancion no encontrada")
        );
        playlist.removeSong(song);
        playlistRepository.save(playlist);
    }
}
