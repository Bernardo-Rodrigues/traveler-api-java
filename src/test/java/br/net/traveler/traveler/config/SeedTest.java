package br.net.traveler.traveler.config;

import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.entities.pk.AchievementUserPk;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Configuration
@Profile("test")
public class SeedTest implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContinentRepository continentRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private TipRepository tipRepository;
    @Autowired
    private AchievementUserRepository achievementUserRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private TravelRepository travelRepository;


    public void run(String... args) throws Exception {
        Continent continent1 = Continent.builder().id(1).name("First Continent").build();
        Continent continent2 = Continent.builder().id(2).name("Second Continent").build();
        continentRepository.saveAll(Arrays.asList(continent1, continent2));

        Country country = Country.builder().id(1).name("Country").continent(continent1).build();
        countryRepository.save(country);

        Localization loc1 = Localization.builder().id(1).lat("0").lng("0").build();
        Localization loc2 = Localization.builder().id(2).lat("1").lng("1").build();
        Localization loc3 = Localization.builder().id(3).lat("2").lng("2").build();
        localizationRepository.saveAll(Arrays.asList(loc1, loc2, loc3));

        Destination destination1 = Destination.builder()
                .id(1)
                .name("First Destination")
                .imageLink("First ImageLink")
                .country(country)
                .localization(loc1)
                .build();
        Destination destination2 = Destination.builder()
                .id(2)
                .name("Second Destination")
                .imageLink("Second ImageLink")
                .country(country)
                .localization(loc2)
                .build();

        Destination destination3 = Destination.builder()
                .id(3)
                .name("Third Destination")
                .imageLink("Third ImageLink")
                .country(country)
                .localization(loc3)
                .build();
        destinationRepository.saveAll(Arrays.asList(destination1, destination2, destination3));

        User user1 = User.builder()
                .id("id1")
                .build();
        User user2 = User.builder()
                .id("id2")
                .build();

        userRepository.saveAll(Arrays.asList(user1, user2));

        Review review1 = Review.builder()
                .id(ReviewsPk.builder().destination(destination1).user(user1).build())
                .note(5)
                .build();
        Review review2 = Review.builder()
                .id(ReviewsPk.builder().destination(destination1).user(user2).build())
                .note(3)
                .build();
        Review review3 = Review.builder()
                .id(ReviewsPk.builder().destination(destination2).user(user1).build())
                .note(3)
                .build();
        reviewRepository.saveAll(Arrays.asList(review1, review2, review3));

        Favorite favorite = Favorite.builder()
                .id(FavoritePk.builder()
                        .user(user1)
                        .destination(destination1)
                        .build()
                ).build();

        favoriteRepository.save(favorite);

        Tip tip1 = Tip.builder()
                .id(1)
                .destination(destination1)
                .description("First tip")
                .build();
        Tip tip2 = Tip.builder()
                .id(2)
                .destination(destination1)
                .description("Second tip")
                .build();

        tipRepository.saveAll(Arrays.asList(tip1, tip2));

        Achievement achievement1 = Achievement.builder()
                .id(1)
                .name("First Achievement")
                .description("First Achievement")
                .destination(destination1)
                .imageLink("Image Link 1")
                .build();

        Achievement achievement2 = Achievement.builder()
                .id(2)
                .name("Second Achievement")
                .description("Second Achievement")
                .destination(destination2)
                .imageLink("Image Link 2")
                .build();

        Achievement achievement3 = Achievement.builder()
                .id(3)
                .name("Third Achievement")
                .description("Third Achievement")
                .destination(destination3)
                .imageLink("Image Link 3")
                .build();

        Achievement achievement4 = Achievement.builder()
                .id(4)
                .name("Count Achievement")
                .description("Count Achievement")
                .count(3)
                .imageLink("Image Link 4")
                .build();

        achievementRepository.saveAll(Arrays.asList(achievement1, achievement2, achievement3, achievement4));

        AchievementUser achievementUser1 = AchievementUser.builder()
                .id(AchievementUserPk.builder()
                        .achievement(achievement1)
                        .user(user1)
                        .build()
                ).build();
        AchievementUser achievementUser2 = AchievementUser.builder()
                .id(AchievementUserPk.builder()
                        .achievement(achievement2)
                        .user(user1)
                        .build()
                ).build();

        achievementUserRepository.saveAll(Arrays.asList(achievementUser1, achievementUser2));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DATE, 2);
        Date tomorrow = calendar.getTime();

        Travel travel1 = Travel.builder()
                .id(1)
                .startDate(Date.from(yesterday.toInstant().plus(8, ChronoUnit.DAYS)))
                .endDate(Date.from(tomorrow.toInstant().plus(8, ChronoUnit.DAYS)))
                .destination(destination1)
                .user(user1)
                .build();

        Travel travel2 = Travel.builder()
                .id(2)
                .startDate(yesterday)
                .endDate(tomorrow)
                .destination(destination2)
                .user(user1)
                .build();

        Travel travel3 = Travel.builder()
                .id(3)
                .startDate(Date.from(yesterday.toInstant().plus(4, ChronoUnit.DAYS)))
                .endDate(Date.from(tomorrow.toInstant().plus(4, ChronoUnit.DAYS)))
                .destination(destination2)
                .user(user1)
                .build();

        travelRepository.saveAll(Arrays.asList(travel1, travel2, travel3));
    }
}
