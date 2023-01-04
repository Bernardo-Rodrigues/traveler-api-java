package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "destinations")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String imageLink;

    @ManyToOne
    @JoinColumn(name = "countryId")
    Country country;

    public Destination(Integer id, String name, String imageLink, Country country){
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.country = country;
    }


}
