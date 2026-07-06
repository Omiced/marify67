package org.generation.marify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private Users user;

    @ManyToMany
    @JoinTable(
            name="playlist_songs",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Songs> songs;
    /*
     para una relacion muchos a muchos, utilizamos @JoinTable
     porque debemos generar una tabla intermedia
     dentro de @JoinTable
     indicaremos
     1. Nombre que va a tener esta tabla
     2. Nombre de la llave foranea de la entidad dueña de la relacion
     3. Nombre de la llave forarena de la entidad dependiente de la relacion
     */

   public void addSong(Songs song){
       this.songs.add(song);
       song.getPlaylists().add(this);
   }

   public void removeSong(Songs song){
       this.songs.remove(song);
       song.getPlaylists().remove(this);
   }
}
