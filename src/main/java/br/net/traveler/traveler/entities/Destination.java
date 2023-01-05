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

    @OneToOne
    @MapsId
    Localization localization;

    public Destination(Integer id, String name, String imageLink, Country country, Localization localization){
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.country = country;
        this.localization = localization;
    }

}
