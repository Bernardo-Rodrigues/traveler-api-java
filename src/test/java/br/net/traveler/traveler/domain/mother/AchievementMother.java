package br.net.traveler.traveler.domain.mother;

import br.net.traveler.traveler.domain.entities.Achievement;
import br.net.traveler.traveler.domain.entities.AchievementUser;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.domain.entities.pk.AchievementUserPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor()
public class AchievementMother {
    public static Achievement getAchievement() {
        return Achievement.builder()
                .id(1)
                .build();
    }
}
