package br.net.traveler.traveler.domain.dto;

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

    private Integer id;
    private Date startDate;
    private Date endDate;
    private Integer destinationId;
    private String userId;
}
