package br.net.traveler.traveler.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destinations")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String imageLink;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    Country country;

    @OneToOne
    @MapsId
    Localization localization;

    @JsonIgnore
    @OneToMany(mappedBy = "destination",  cascade = CascadeType.ALL)
    List<Tip> tips = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "destination",  cascade = CascadeType.ALL)
    List<Description> descriptions = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL)
    Achievement achievement;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    List<Travel> travels = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.destination", cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    public Destination(Integer id, String name, String imageLink, Country country, Localization localization){
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.country = country;
        this.localization = localization;
    }

}
