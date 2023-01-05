package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "descriptions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(columnDefinition = "text")
    String text;

    String type;

    @ManyToOne
    @JoinColumn(name = "destinationId")
    Destination destination;
}
