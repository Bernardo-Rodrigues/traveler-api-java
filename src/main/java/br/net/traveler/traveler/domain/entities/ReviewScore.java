package br.net.traveler.traveler.domain.entities;

import br.net.traveler.traveler.domain.entities.pk.ReviewsPk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

public interface ReviewScore {

    Integer getDestinationId();
    Double getScore();

}
