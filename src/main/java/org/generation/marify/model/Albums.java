package org.generation.marify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Albums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
        No es necesario poner la anotacion @Column
        al menos que queramos sobreescribbir un valor por defecto
        Valores por defecto
        nullable = true
        unique = false
        insertable = true
        updatable = true
        name = nombre del atrributo
     */
    @NotBlank
    private String name;
    // yyyy-MM-dd iso 8601 format
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = false)
    @JsonIgnore
    private Artists artist;
}
