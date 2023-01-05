package br.net.traveler.traveler.config;

import br.net.traveler.traveler.entities.Continent;
import br.net.traveler.traveler.entities.Country;
import br.net.traveler.traveler.entities.Destination;
import br.net.traveler.traveler.entities.Localization;
import br.net.traveler.traveler.repositories.ContinentRepository;
import br.net.traveler.traveler.repositories.CountryRepository;
import br.net.traveler.traveler.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Seed implements CommandLineRunner {

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public void run(String... args) throws Exception {
        Continent continent1 = Continent.builder().id(1).name("South America").build();
        Continent continent2 = Continent.builder().id(2).name("North America").build();
        Continent continent3 = Continent.builder().id(3).name("Europe").build();
        Continent continent4 = Continent.builder().id(4).name("Africa").build();
        Continent continent5 = Continent.builder().id(5).name("Asia").build();
        Continent continent6 = Continent.builder().id(6).name("Oceania").build();
        Continent continent7 = Continent.builder().id(7).name("Antarctica").build();

        continentRepository.saveAll(Arrays.asList(continent1, continent2, continent3, continent4, continent5, continent6, continent7));

        Country country1 = Country.builder().id(1).name("Peru").continent(continent1).build();
        Country country2 = Country.builder().id(2).name("Nepal").continent(continent5).build();
        Country country3 = Country.builder().id(3).name("France").continent(continent3).build();
        Country country4 = Country.builder().id(4).name("Brazil").continent(continent1).build();
        Country country5 = Country.builder().id(5).name("Italy").continent(continent3).build();
        Country country6 = Country.builder().id(6).name("Indonesia").continent(continent5).build();

        countryRepository.saveAll(Arrays.asList(country1, country2, country3, country4, country5, country6));

        Localization loc1 = Localization.builder().id(1).lat("-13.163068").lng("-72.545128").build();
        Localization loc2 = Localization.builder().id(2).lat("27.986065").lng("86.922623").build();
        Localization loc3 = Localization.builder().id(3).lat("48.864716").lng("2.349014").build();
        Localization loc4 = Localization.builder().id(4).lat("-22.908333").lng("-43.196388").build();
        Localization loc5 = Localization.builder().id(5).lat("41.902782").lng("-72.545128").build();
        Localization loc6 = Localization.builder().id(6).lat("-8.275").lng("115.166").build();

        Destination destination1 = Destination.builder()
                .id(1)
                .name("Machu Picchu")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Machu-Picchu.jpg")
                .country(country1)
                .localization(loc1)
                .build();
        Destination destination2 = Destination.builder()
                .id(2)
                .name("Mount Everest")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Mount-Everest.jpg")
                .country(country2)
                .localization(loc2)
                .build();
        Destination destination3 = Destination.builder()
                .id(3)
                .name("Paris")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Paris.jpg")
                .country(country3)
                .localization(loc3)
                .build();
        Destination destination4 = Destination.builder()
                .id(4)
                .name("Rio de Janeiro")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rio-de-Janeiro.jpg")
                .country(country4)
                .localization(loc4)
                .build();
        Destination destination5 = Destination.builder()
                .id(5)
                .name("Rome")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rome.jpg")
                .country(country5)
                .localization(loc5)
                .build();
        Destination destination6 = Destination.builder()
                .id(6)
                .name("Bali")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Bali.jpg")
                .country(country6)
                .localization(loc6)
                .build();

        destinationRepository.saveAll(Arrays.asList(destination1, destination2, destination3, destination4, destination5, destination6));
    }
}
