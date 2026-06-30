package org.generation.marify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/*con la anotacion entity indicamos
  que esta clase es una representación de una tabla de
  nuestra base de datos.
*/
@Entity
public class Artists {
    /**
     * Atributo que representa el primary key de la tabla
     * la estrategia de generaion va a ser
     * empezando de 1 y subiendo de uno en uno
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
    * String = varchar
    * tamaño por defecto 255 caracteres
    * */
    @Column(length = 100)
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String genere;

    //declarando la relacion del lado del dueño
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Albums> albums;

    public Artists(Long id, String name, String genere) {
        this.id = id;
        this.name = name;
        this.genere = genere;
    }

    public Artists() {
    }

    public List<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Albums> albums) {
        this.albums = albums;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
