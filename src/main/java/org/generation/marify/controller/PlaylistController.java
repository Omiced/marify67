package org.generation.marify.controller;

import lombok.AllArgsConstructor;
import org.generation.marify.dto.PlaylistRequest;
import org.generation.marify.model.Playlist;
import org.generation.marify.service.PlaylistService;
import org.generation.marify.service.PlaylistSongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api/playlist")
@AllArgsConstructor
public class PlaylistController {
    private PlaylistService playlistService;
    private PlaylistSongService playlistSongService;

    @GetMapping
    public List<Playlist> getAllPlaylist(){
        return playlistService.getAllPlaylists();
    }

    @GetMapping(path="{playlistId}")
    public Playlist getPlaylistById(@PathVariable("playlistId") Long id ){
        return playlistService.getSinglePlaylist(id);
    }

    @PostMapping
    public Playlist addPlaylist(@RequestBody PlaylistRequest playlistRequest){
        return playlistService.createPlaylist(playlistRequest);
    }
    //http://localhost:8080/api/playlist/1/song/1
    @PostMapping(path = "{playlistId}/song/{songId}")
    public void addSongToPlaylist(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId){
        playlistSongService.addSongToPlaylist(playlistId,songId);
    }

    @DeleteMapping(path = "{playlistId}/song/{songId}")
    public void removeSongFromPlaylist(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId){
        playlistSongService.removeSongFromPlaylist(playlistId, songId);
    }


}
