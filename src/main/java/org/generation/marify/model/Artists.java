package org.generation.marify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    public Artists(Long id, String name, String genere) {
        this.id = id;
        this.name = name;
        this.genere = genere;
    }

    public Artists() {
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
