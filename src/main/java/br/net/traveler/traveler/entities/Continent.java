package br.net.traveler.traveler.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "continent",  cascade = CascadeType.ALL)
    List<Country> countries = new ArrayList<>();

    public Continent(Integer id, String name){
        this.id = id;
        this.name = name;
    }

}
