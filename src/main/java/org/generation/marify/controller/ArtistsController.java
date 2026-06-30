package org.generation.marify.controller;

import org.generation.marify.model.Artists;
import org.generation.marify.service.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
anotaciones del controlador
1. RestController lo cual indica que es un controlador tipo rest full,
    o sea vamos a tener una api con endpoints
2. RequestMapping aqui vamos a indicar que url tiene este endpoint
 */
@RestController
@RequestMapping(path = "/api/artists")// http://localhost:8080/api/artists
public class ArtistsController {
    private final ArtistsService artistsService;
    //inyectando el servio de artista
    @Autowired
    public ArtistsController(ArtistsService artistsService) {
        this.artistsService = artistsService;
    }

    @GetMapping //http://localhost:8080/api/artists
    public List<Artists> getAllArtists(){
        return artistsService.getAllArtists();
    }

    @GetMapping(path="{artistId}") // http://localhost:8080/api/artists/artistId
    // ejemplo http://localhost:8080/api/artists/1
    public Artists getSingleArtist(@PathVariable("artistId") Long id){
        return artistsService.getArtistById(id);
    }

    @PostMapping //http://localhost:8080/api/artists con un body y con el metodo POST
    public Artists addArtist(@RequestBody Artists artist){
        return artistsService.createArtist(artist);
    }

    @DeleteMapping(path="{artistId}")// http://localhost:8080/api/artists/id metodo DELETE
    public Artists deleteArtistById(@PathVariable("artistId")
                                        Long id){
        return artistsService.deleteArtistById(id);
    }

    @PutMapping(path = "{artistId}") // http://localhost:8080/api/artists/id con un body y metodo PUT
    public Artists updateArtist(@PathVariable("artistId") Long id, @RequestBody Artists updatedArtist){
        return artistsService.updateArtistById(id, updatedArtist);
    }

}
