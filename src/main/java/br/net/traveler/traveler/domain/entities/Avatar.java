package br.net.traveler.traveler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;

    @Column(unique = true)
    private String imageLink;

    private Integer tripsCount;

    @JsonIgnore
    @OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

}
