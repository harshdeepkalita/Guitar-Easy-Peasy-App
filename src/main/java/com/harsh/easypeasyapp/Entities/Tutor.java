package com.harsh.easypeasyapp.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "tutor_genre_type",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_type_id")
    )
    private Set<GenreType> genreTypes;

    @ManyToMany
    @JoinTable(
            name = "tutor_guitar_type",
            joinColumns = @JoinColumn(name="tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "guitar_type_id")
    )
    private Set<GuitarType> guitarTypes;
    private Integer experienceLevel;
}
