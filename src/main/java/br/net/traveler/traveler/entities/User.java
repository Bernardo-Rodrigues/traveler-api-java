package br.net.traveler.traveler.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String username;
    String email;
    String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatarId")
    Avatar avatar;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "titleId")
    Title title;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Travel> travels = new ArrayList<>();

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

}
