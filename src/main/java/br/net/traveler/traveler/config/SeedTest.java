package br.net.traveler.traveler.config;

import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.domain.entities.pk.FavoritePk;
import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.repositories.*;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

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
    private UserService userService;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private TipRepository tipRepository;

    public void run(String... args) throws Exception {
        Continent continent1 = Continent.builder().id(1).name("First Continent").build();
        Continent continent2 = Continent.builder().id(2).name("Second Continent").build();
        continentRepository.saveAll(Arrays.asList(continent1, continent2));

        Country country = Country.builder().id(1).name("Country").continent(continent1).build();
        countryRepository.save(country);

        Localization loc1 = Localization.builder().id(1).lat("0").lng("0").build();
        Localization loc2 = Localization.builder().id(2).lat("1").lng("1").build();
        localizationRepository.saveAll(Arrays.asList(loc1, loc2));

        Destination destination1 = Destination.builder()
                .id(1)
                .name("First Destination")
                .imageLink("First ImageLink")
                .country(country)
                .localization(loc1)
                .build();
        Destination destination2 = Destination.builder()
                .id(2)
                .name("Second Destination 2")
                .imageLink("Second ImageLink 2")
                .country(country)
                .localization(loc2)
                .build();
        destinationRepository.saveAll(Arrays.asList(destination1, destination2));

        User user1 = User.builder()
                .id(1)
                .username("user 1")
                .email("email1@email.com")
                .password("password1")
                .build();
        User user2 = User.builder()
                .id(2)
                .username("user 2")
                .email("email2@email.com")
                .password("password2")
                .build();

        userService.createUser(userMapper.entityToDto(user1));
        userService.createUser(userMapper.entityToDto(user2));

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
    }
}
