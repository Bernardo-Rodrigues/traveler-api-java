package br.net.traveler.traveler.domain.entities;

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
    private Integer id;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinationId")
    private Destination destination;
}
