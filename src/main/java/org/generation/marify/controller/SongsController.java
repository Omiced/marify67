package org.generation.marify.controller;

import lombok.AllArgsConstructor;
import org.generation.marify.dto.SongsRequest;
import org.generation.marify.model.Songs;
import org.generation.marify.service.SongsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/songs")
@AllArgsConstructor
public class SongsController {
    private final SongsService songsService;

    @GetMapping //http:localhost:8080/api/songs
    public List<Songs> getAllSongs(){
        return songsService.getAllSongs();
    }

    @GetMapping(path = "{songId}")//http:localhost:8080/api/songs/id
    public Songs getSongById(@PathVariable("songId") Long id){
        return songsService.getSong(id);
    }

    @PostMapping //http:localhost:8080/api/songs pero metodo POST
    public Songs addSong(@RequestBody SongsRequest songsRequest){
        return songsService.addSong(songsRequest);
    }

    @DeleteMapping(path="{songId}") //http:localhost:8080/api/songs/id metodo DELETE
    public Songs deleteSongById(@PathVariable("songId") Long id){
        return songsService.deleteSongById(id);
    }
}
