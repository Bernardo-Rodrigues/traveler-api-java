package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tips")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(columnDefinition = "text")
    String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinationId")
    Destination destination;
}
