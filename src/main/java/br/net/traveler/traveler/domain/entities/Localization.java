package br.net.traveler.traveler.domain.entities;

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
    private Integer id;
    private String lat;
    private String lng;

    @JsonIgnore
    @OneToOne(mappedBy = "localization", cascade = CascadeType.ALL)
    private Destination destination;

}
