package br.net.traveler.traveler.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "localizations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String lat;
    String lng;

    @JsonIgnore
    @OneToOne(mappedBy = "localization", cascade = CascadeType.ALL)
    Destination destination;

    public Localization(Integer id, String lat, String lng){
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

}
