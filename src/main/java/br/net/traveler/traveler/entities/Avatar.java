package br.net.traveler.traveler.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "avatars")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String imageLink;
    Integer tripsCount;

    @OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
    List<User> users = new ArrayList<>();
}
