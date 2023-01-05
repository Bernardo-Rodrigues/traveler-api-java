package br.net.traveler.traveler.config;

import br.net.traveler.traveler.entities.*;
import br.net.traveler.traveler.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class Seed implements CommandLineRunner {

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private LocalizationRepository localizationRepository;

    @Autowired
    private TipRepository tipRepository;

    @Override
    public void run(String... args) throws Exception {
        Continent continent1 = Continent.builder().name("South America").build();
        Continent continent2 = Continent.builder().name("North America").build();
        Continent continent3 = Continent.builder().name("Europe").build();
        Continent continent4 = Continent.builder().name("Africa").build();
        Continent continent5 = Continent.builder().name("Asia").build();
        Continent continent6 = Continent.builder().name("Oceania").build();
        Continent continent7 = Continent.builder().name("Antarctica").build();

        continentRepository.saveAll(Arrays.asList(continent2, continent4, continent6, continent7));

        Country country1 = Country.builder().name("Peru").continent(continent1).build();
        Country country2 = Country.builder().name("Nepal").continent(continent5).build();
        Country country3 = Country.builder().name("France").continent(continent3).build();
        Country country4 = Country.builder().name("Brazil").continent(continent1).build();
        Country country5 = Country.builder().name("Italy").continent(continent3).build();
        Country country6 = Country.builder().name("Indonesia").continent(continent5).build();

        Localization loc1 = Localization.builder().lat("-13.163068").lng("-72.545128").build();
        Localization loc2 = Localization.builder().lat("27.986065").lng("86.922623").build();
        Localization loc3 = Localization.builder().lat("48.864716").lng("2.349014").build();
        Localization loc4 = Localization.builder().lat("-22.908333").lng("-43.196388").build();
        Localization loc5 = Localization.builder().lat("41.902782").lng("12.496366").build();
        Localization loc6 = Localization.builder().lat("-8.275").lng("115.166").build();

        Destination destination1 = Destination.builder()
                .name("Machu Picchu")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Machu-Picchu.jpg")
                .country(country1)
                .localization(loc1)
                .build();
        Destination destination2 = Destination.builder()
                .name("Mount Everest")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Mount-Everest.jpg")
                .country(country2)
                .localization(loc2)
                .build();
        Destination destination3 = Destination.builder()
                .name("Paris")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Paris.jpg")
                .country(country3)
                .localization(loc3)
                .build();
        Destination destination4 = Destination.builder()
                .name("Rio de Janeiro")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rio-de-Janeiro.jpg")
                .country(country4)
                .localization(loc4)
                .build();
        Destination destination5 = Destination.builder()
                .name("Rome")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rome.jpg")
                .country(country5)
                .localization(loc5)
                .build();
        Destination destination6 = Destination.builder()
                .name("Bali")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Bali.jpg")
                .country(country6)
                .localization(loc6)
                .build();

        Tip tip1 = Tip.builder()
                .description("Food in Machu Picchu is expensive and super limited. Take a fancy Picnic or something to satisfy your hunger and leave for a fancy lunch in the afternoon on the way back to Aguas Calientes. Having a picnic sitting on top of the mountain is something incredible.")
                .destination(destination1)
                .build();
        Tip tip2 = Tip.builder()
                .description("Even on cloudy days the solar radiation in Peru is strong and it burns for real. Bring sunscreen (and sunglasses) and repeat the application as often as you remember.")
                .destination(destination1)
                .build();
        Tip tip3 = Tip.builder()
                .description("Don't forget to bring appropriate clothes according to the climate of each destination. In the case of Mount Everest, the average temperature is -36o C. Therefore, be sure to wear appropriate clothing for the journey that can take up to four days.")
                .destination(destination2)
                .build();
        Tip tip4 = Tip.builder()
                .description("To protect the skin from ultraviolet rays, the ideal is to apply sunscreen every four hours. This should be done even when the weather is not very sunny.")
                .destination(destination2)
                .build();
        Tip tip5 = Tip.builder()
                .description("As in most Catholic countries, most local businesses are closed on Sundays. Because of this, plan to buy items from the market or pharmacy in advance.")
                .destination(destination3)
                .build();
        Tip tip6 = Tip.builder()
                .description("If there's one thing you can't miss in Paris, it's trying French pastries. The French patisserie is world famous and you've probably heard of sweets like macaron and crème brulée.")
                .destination(destination3)
                .build();
        Tip tip7 = Tip.builder()
                .description("On the weekends, tourists flock to Rio de Janeiro in huge numbers. This means traffic and crowds at all top attractions like Christ the Redeemer (Cristo Redentor).  If you can visit on a weekday, you should be able to save yourself some stress and make your experience a little better.")
                .destination(destination4)
                .build();
        Tip tip8 = Tip.builder()
                .description("Speaking of what to wear in Rio de Janeiro, make sure to get yourself some Havaianas as soon as you get to Brazil!  These charming flip-flops are the most comfortable kind of footwear in Brazil, and you can get them at a great price. Also, they are like the official dress code of Rio.")
                .destination(destination4)
                .build();
        Tip tip9 = Tip.builder()
                .description("Buy the Roma Pass when you arrive at the airport. If you're going to visit the basic attractions (the Coliseum, the Galleria Borghese, the Capitoline Museum), the pass is great. It includes two free entries (of your choice) and reduced entry to several others, as well as giving you the right to use all public transport in the city for the duration of the pass.")
                .destination(destination5)
                .build();
        Tip tip10 = Tip.builder()
                .description("If you're traveling to Rome on a budget or otherwise, don't eve bother paying for water. The city has water fountains, affectionately called 'nasoni' or little noses around every other corner and these fountains have good to drink water flowing from it 24*7.")
                .destination(destination5)
                .build();
        Tip tip11 = Tip.builder()
                .description("In Machu Picchu there are public restrooms but they are located outside the enclosure, next to the main entrance. If you are inside the archaeological site, it is possible to go out to use the bathroom and enter again without going through the lines.")
                .destination(destination1)
                .build();
        Tip tip12 = Tip.builder()
                .description("There are two ways to visit the Eiffel Tower: you can go up to the top for a breathtaking view, or just go up to the second floor and still have an amazing view from up there!")
                .destination(destination3)
                .build();
        Tip tip13 = Tip.builder()
                .description("At the center of Bali is Ubud, considered the island's cultural hotspot. Galleries, museums, palaces, hidden temples and typical crafts are what make the city vibrant, in addition to the welcoming people. Adventurers also find many activities there, such as trekking, rafting, climbing the volcano on Mount Batur and even riding an elephant.")
                .destination(destination6)
                .build();
        Tip tip14 = Tip.builder()
                .description("The beautiful beaches are concentrated in the south of the island, such as Bukit, Padang Padang, Uluwatu, Jimbaran and Seminyak, the latter two being the most frequented by those who appreciate beach clubs and good restaurants. Comfort seekers look to the luxurious resorts of Nusa Dua, dominated by family-friendly hotels.")
                .destination(destination6)
                .build();
        Tip tip15 = Tip.builder()
                .description("One of the main activities for those who travel to the so-called island of the gods, as Bali is also known, is to visit and be dazzled by beautiful and very different temples.")
                .destination(destination6)
                .build();

        tipRepository.saveAll(Arrays.asList(tip1, tip2, tip3, tip4, tip5, tip6, tip7, tip8,
                                            tip9, tip10, tip11, tip12, tip13, tip14, tip15));
    }
}
