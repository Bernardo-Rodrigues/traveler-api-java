package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "continents")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @OneToMany(mappedBy = "continent")
    List<Country> countries = new ArrayList<>();

    public Continent(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
