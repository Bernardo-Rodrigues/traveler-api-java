package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continentId")
    Continent continent;

    @OneToMany(mappedBy = "country",  cascade = CascadeType.ALL)
    List<Destination> destinations = new ArrayList<>();

    public Country(Integer id, String name, Continent continent){
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

}
