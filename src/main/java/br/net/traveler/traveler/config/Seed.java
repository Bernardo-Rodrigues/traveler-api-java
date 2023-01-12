package br.net.traveler.traveler.config;

import br.net.traveler.traveler.domain.entities.*;
import br.net.traveler.traveler.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("prod")
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
    @Autowired
    private DescriptionRepository descriptionRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private AvatarRepository avatarRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        Continent continent1 = Continent.builder().name("South America").build();
        Continent continent2 = Continent.builder().name("North America").build();
        Continent continent3 = Continent.builder().name("Europe").build();
        Continent continent4 = Continent.builder().name("Africa").build();
        Continent continent5 = Continent.builder().name("Asia").build();
        Continent continent6 = Continent.builder().name("Oceania").build();
        Continent continent7 = Continent.builder().name("Antarctica").build();

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

        Description description1 = Description.builder()
                .text("Huayna Picchu Picchu or Machu Picchu (in Quechua Machu Picchu, \"old mountain\"), also called \"lost city of the Incas\", is a well-preserved pre-Columbian city, located on top of a mountain, at an altitude of 2,400 meters, in the valley of the Urubamba River, present-day Peru.")
                .type("About Destination")
                .destination(destination1)
                .build();
        Description description2 = Description.builder()
                .text("Mount Everest  is Earth's highest mountain above sea level, located in the Mahalangur Himal sub-range of the Himalayas. The China–Nepal border runs across its summit point. Its elevation (snow height) of 8,848.86 m (29,031.7 ft) was most recently established in 2020 by the Chinese and Nepali authorities.")
                .type("About Destination")
                .destination(destination2)
                .build();
        Description description3 = Description.builder()
                .text("Paris is the capital and most populous city in France, with an estimated 2020 population of 2,148,271 in an area of 105 square kilometers. second most expensive city in the world, behind only Singapore and ahead of Zurich, Hong Kong, Oslo and Geneva.")
                .type("About Destination")
                .destination(destination3)
                .build();
        Description description4 = Description.builder()
                .text("Rio de Janeiro is a Brazilian municipality, capital of the homonymous state, located in the Southeast of the country. Largest international tourist destination in Brazil, Latin America and the entire Southern Hemisphere (in 2008), the capital of Rio de Janeiro is the best known Brazilian city abroad, functioning as a national \"mirror\", or \"portrait\".")
                .type("About Destination")
                .destination(destination4)
                .build();
        Description description5 = Description.builder()
                .text("Rome is the capital city of Italy. It is also the capital of the Lazio region, the centre of the Metropolitan City of Rome, and a special comune named Comune di Roma Capitale. With 2,860,009 residents in 1,285 km2 (496.1 sq mi), Rome is the country's most populated comune and the third most populous city in the European Union.")
                .type("About Destination")
                .destination(destination5)
                .build();
        Description description6 = Description.builder()
                .text("The Spanish conqueror Baltasar de Ocampo had notes of a visit during the late 16th century to a mountain fortress called Pitcos with very sumptuous and majestic buildings, erected with great skill and art, all the lintels of the doors, as well as the main and the vulgar, being of marble, elaborately carved. Therefore, we can consider him the first discoverer from outside the region.")
                .type("Discoverer")
                .destination(destination1)
                .build();
        Description description7 = Description.builder()
                .text("Mount Everest has been host to other winter sports and adventuring besides mountaineering, including snowboarding, skiing, paragliding, and BASE jumping.")
                .type("Extreme Sports")
                .destination(destination2)
                .build();
        Description description8 = Description.builder()
                .text("In 2008, a new weather station at about 8,000 m (26,000 ft) elevation went online.The station's first data in May 2008 were air temperature −17 °C (1 °F), relative humidity 41.3 per cent, atmospheric pressure 382.1 hPa (38.21 kPa), wind direction 262.8°, wind speed 12.8 m/s (28.6 mph, 46.1 km/h), global solar radiation 711.9 watts/m2, solar UVA radiation 30.4 W/m2.")
                .type("Meteorology")
                .destination(destination2)
                .build();
        Description description9 = Description.builder()
                .text("Paris Saint-Germain football club and Stade Français rugby club are based in Paris. The 81,000-seat Stade de France, built for the 1998 FIFA World Cup, is located north of the city, in the neighboring commune of Saint-Denis. Paris annually organizes the Grand Slam tennis tournament. It hosted the 1900 and 1924 Summer Olympics, and is expected to host the 2024. Paris was also the host city for the 1938 and 1998 FIFA World Cups, the 2007 Rugby Union World Cup and the European Football Championship in 1960, 1984 and 2016. The Tour de France road cycling competition ends in Paris every July")
                .type("Sports")
                .destination(destination3)
                .build();
        Description description10 = Description.builder()
                .text("Its coastline is 197 kilometers long and includes more than one hundred islands that occupy 37 km², and is divided into three parts, facing Sepetiba Bay, the Atlantic Ocean and Guanabara Bay.")
                .type("Coastline")
                .destination(destination4)
                .build();
        Description description11 = Description.builder()
                .text("The city experiences hot, humid summers and warm, sunny winters. In the inner areas of the city, temperatures above 40 °C are common during the summer, although rarely for long periods, while maximum temperatures above 23 °C can occur monthly.")
                .type("Climate")
                .destination(destination4)
                .build();
        Description description12 = Description.builder()
                .text("In 2019, Rome was the 14th most visited city in the world, with 8.6 million tourists, the third most visited in the European Union, and the most popular tourist destination in Italy. Rome today is one of the most important tourist destinations of the world, due to the incalculable immensity of its archaeological and artistic treasures, as well as for the charm of its unique traditions, the beauty of its panoramic views, and the majesty of its magnificent \"villas\" (parks).")
                .type("Tourism")
                .destination(destination5)
                .build();
        Description description13 = Description.builder()
                .text("Bali is an island and province of Indonesia, situated at the western end of the Lesser Sunda Islands archipelago, between the islands of Java (to the west) and Lombok (to the east). roughly in the middle of the south coast.It is known for the cultural manifestations of its people, such as dance, sculpture, painting, leather and metal work and music. Bali is part of the Coral Triangle, a maritime area of \u200B\u200Bextremely high biodiversity, where more than 500 species of coral are found (76% of the number known worldwide).")
                .type("About")
                .destination(destination6)
                .build();
        Description description14 = Description.builder()
                .text("The name Bali, with which the island was named in the 9th century, derives from the word Wali. Wali or Wari was the term by which the natives, who greatly venerated their gods, called the act of worship. Wali is a Sanskrit word meaning \"sacrifice offered to the god\", \"worship\", \"worship\" or \"offering\".")
                .type("Etymology")
                .destination(destination6)
                .build();

        descriptionRepository.saveAll(Arrays.asList(description1, description2, description3, description4, description5, description6,
                                                    description7, description8,  description9, description10, description11, description12,
                                                    description13, description14));

        Achievement achievement1 = Achievement.builder()
                .name("Machu Picchu")
                .description("You visited Machu Picchu!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Machu-Picchu.jpg")
                .destination(destination1)
                .build();
        System.out.println(achievement1);
        Achievement achievement2 = Achievement.builder()
                .name("Mount Everest")
                .description("You visited Mount Everest!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Mount-Everest.jpg")
                .destination(destination2)
                .build();
        Achievement achievement3 = Achievement.builder()
                .name("Paris")
                .description("You visited Paris!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Paris.jpg")
                .destination(destination3)
                .build();
        Achievement achievement4 = Achievement.builder()
                .name("Rio de Janeiro")
                .description("You visited Rio de Janeiro!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rio-de-Janeiro.jpg")
                .destination(destination4)
                .build();
        Achievement achievement5 = Achievement.builder()
                .name("Rome")
                .description("You visited Rome!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Rome.jpg")
                .destination(destination5)
                .build();
        Achievement achievement6 = Achievement.builder()
                .name("Bali")
                .description("You visited Bali!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/destinies/Bali.jpeg")
                .destination(destination6)
                .build();
        Achievement achievement7 = Achievement.builder()
                .name("Beginner Traveler")
                .description("You visited 3 destinations!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/achievements/beginner.jpg")
                .count(3)
                .build();
        Achievement achievement8 = Achievement.builder()
                .name("Intermediate Traveler")
                .description("You visited 5 destinations!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/achievements/intermidiate.jpg")
                .count(5)
                .build();
        Achievement achievement9 = Achievement.builder()
                .name("Experient Traveler")
                .description("You visited 10 destinations!")
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/achievements/experient.jpg")
                .count(10)
                .build();

        achievementRepository.saveAll(Arrays.asList(achievement1, achievement2, achievement3, achievement4, achievement5,
                                                    achievement6, achievement7, achievement8, achievement9));

        Avatar avatar1 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Boy-1")
                .build();
        Avatar avatar2 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Boy-2")
                .build();
        Avatar avatar3 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Boy-3")
                .build();
        Avatar avatar4 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Girl-1")
                .build();
        Avatar avatar5 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Girl-2")
                .build();
        Avatar avatar6 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Girl-3")
                .build();
        Avatar avatar7 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Boy-Traveler")
                .tripsCount(3)
                .build();
        Avatar avatar8 = Avatar.builder()
                .imageLink("https://hjjvsmpqvznxkydtrqzo.supabase.co/storage/v1/object/public/avatars/Girl-Traveler")
                .tripsCount(3)
                .build();

        avatarRepository.saveAll(Arrays.asList(avatar1, avatar2, avatar3, avatar4, avatar5, avatar6, avatar7, avatar8));

        Title title1 = Title.builder()
                .text("Curious")
                .build();
        Title title2 = Title.builder()
                .text("Beginner Traveler")
                .tripsCount(3)
                .build();
        Title title3 = Title.builder()
                .text("Intermediate Traveler")
                .tripsCount(5)
                .build();
        Title title4 = Title.builder()
                .text("Experient Traveler")
                .tripsCount(10)
                .build();

        titleRepository.saveAll(Arrays.asList(title1, title2, title3, title4));

        continentRepository.saveAll(Arrays.asList(continent2, continent4, continent6, continent7));
    }
}
