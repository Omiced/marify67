package org.generation.marify.controller;

import lombok.AllArgsConstructor;
import org.generation.marify.model.Songs;
import org.generation.marify.service.SongsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/songs")
@AllArgsConstructor
public class SongsController {
    private final SongsService songsService;

    @GetMapping
    public List<Songs> getAllSongs(){
        return songsService.getAllSongs();
    }

    @GetMapping(path = "{songId}")
    public Songs getSongById(@PathVariable("songId") Long id){
        return songsService.getSong(id);
    }
}
