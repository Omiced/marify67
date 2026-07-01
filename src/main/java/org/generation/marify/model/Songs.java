package org.generation.marify.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 1. hacer esta clase un entidad
 * 2. poner los atributos name y duration, name tipo texto, duration tipo numero
 * 3. usar lombok para,tener setters y getters
 * tambien los constructores vacio y con todo
 * 4. No olvidar su id
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @Min(30)
    @Max(200)
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    @JsonIgnore
    private Albums album;
}
