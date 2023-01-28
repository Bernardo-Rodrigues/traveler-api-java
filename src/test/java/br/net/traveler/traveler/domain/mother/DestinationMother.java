package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class DestinationMother {
    public static Destination getDestination() {
        return Destination.builder()
                .id(1)
                .name("destination")
                .imageLink("imageLink")
                .localization(Localization.builder()
                        .id(1)
                        .build()
                ).country(Country.builder()
                        .id(1)
                        .build()
                ).build();
    }
}
