package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "achievements")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String description;
    String imageLink;
    Integer count;

    @OneToOne
    @JoinColumn(name = "destinationId")
    Destination destination;

    @OneToMany(mappedBy = "id.achievement", cascade = CascadeType.ALL)
    List<AchievementUser> achievements = new ArrayList<>();

}
