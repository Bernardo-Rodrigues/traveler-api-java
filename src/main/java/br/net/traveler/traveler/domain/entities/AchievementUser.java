package br.net.traveler.traveler.domain.entities;

import br.net.traveler.traveler.domain.entities.pk.AchievementUserPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "achievementsUsers")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementUser {

    @EmbeddedId
    private AchievementUserPk id;

}
