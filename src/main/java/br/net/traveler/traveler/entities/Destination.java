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
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String imageLink;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    private Country country;

    @OneToOne
    @MapsId
    private Localization localization;

    @JsonIgnore
    @OneToMany(mappedBy = "destination",  cascade = CascadeType.ALL)
    private List<Tip> tips = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "destination",  cascade = CascadeType.ALL)
    private List<Description> descriptions = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL)
    private Achievement achievement;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Travel> travels = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.destination", cascade = CascadeType.ALL)
    private  List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.destination", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

}
