package br.net.traveler.traveler.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Date startDate;
    Date endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinationId")
    Destination destination;
}
