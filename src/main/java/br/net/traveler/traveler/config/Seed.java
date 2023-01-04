package br.net.traveler.traveler.config;

import br.net.traveler.traveler.entities.Continent;
import br.net.traveler.traveler.entities.Country;
import br.net.traveler.traveler.entities.Destination;
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
        Continent continent1 = new Continent(1, "South America");
        Continent continent2 = new Continent(2, "North America");
        Continent continent3 = new Continent(3, "Europe");
        Continent continent4 = new Continent(4, "Africa");
        Continent continent5 = new Continent(5, "Asia");
        Continent continent6 = new Continent(6, "Oceania");
        Continent continent7 = new Continent(7, "Antarctica");

        continentRepository.saveAll(Arrays.asList(continent1, continent2, continent3, continent4, continent5, continent6, continent7));

        Country country1 = new Country(1, "Peru", continent1);
        Country country2 = new Country(2, "Nepal", continent5);
        Country country3 = new Country(3, "France", continent3);
        Country country4 = new Country(4, "Brazil", continent1);
        Country country5 = new Country(5, "Italy", continent3);
        Country country6 = new Country(6, "Indonesia", continent5);

        countryRepository.saveAll(Arrays.asList(country1, country2, country3, country4, country5, country6));

        Destination destination1 = new Destination(
                1,
                "Machu Picchu",
                "https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Machu-Picchu.jpg",
                country1
        );
        Destination destination2 = new Destination(
                2,
                "Mount Everest",
                "https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Mount-Everest.jpg",
                country2
        );
        Destination destination3 = new Destination(
                3,
                "Paris",
                "https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Paris.jpg",
                country3
        );
        Destination destination4 = new Destination(
                4,
                "Rio de Janeiro",
                "https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rio-de-Janeiro.jpg",
                country4
        );
        Destination destination5 = new Destination(
                5,
                "Rome",
                "https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rome.jpg",
                country5
        );
        Destination destination6 = new Destination(
                6,
                "Bali",
                "https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Bali.jpg",
                country6
        );

        destinationRepository.saveAll(Arrays.asList(destination1, destination2, destination3, destination4, destination5, destination6));
    }
}
