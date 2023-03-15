package br.net.traveler.traveler.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String id;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Travel> travels = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    private List<AchievementUser> achievements = new ArrayList<>();

}
