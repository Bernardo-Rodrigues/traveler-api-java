package br.net.traveler.traveler.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer id;

    @Column(unique = true)
    private String text;

    private Integer tripsCount;

    @JsonIgnore
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

}
