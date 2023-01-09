package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "titles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String text;
    Integer tripsCount;

    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();
}
