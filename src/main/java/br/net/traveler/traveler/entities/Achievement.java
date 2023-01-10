package br.net.traveler.traveler.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;

    private String name;
    private String description;
    private String imageLink;

    @Column(unique = true)
    private Integer count;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "destinationId", unique = true)
    private Destination destination;

    @JsonIgnore
    @OneToMany(mappedBy = "id.achievement", cascade = CascadeType.ALL)
    private List<AchievementUser> achievements = new ArrayList<>();

}
