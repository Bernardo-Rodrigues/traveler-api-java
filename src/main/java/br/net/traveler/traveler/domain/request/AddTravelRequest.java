package br.net.traveler.traveler.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTravelRequest {

    private Integer destinationId;
    private Date startDate;
    private Date endDate;

}
