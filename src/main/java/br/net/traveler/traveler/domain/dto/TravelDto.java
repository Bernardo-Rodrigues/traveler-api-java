package br.net.traveler.traveler.domain.dto;

import br.net.traveler.traveler.domain.entities.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelDto {

    private Date startDate;
    private Date endDate;
    private Destination destination;
}
