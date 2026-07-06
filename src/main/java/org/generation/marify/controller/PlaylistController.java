package org.generation.marify.controller;

import lombok.AllArgsConstructor;
import org.generation.marify.dto.PlaylistRequest;
import org.generation.marify.model.Playlist;
import org.generation.marify.service.PlaylistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api/playlist")
@AllArgsConstructor
public class PlaylistController {
    private PlaylistService playlistService;

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
}
