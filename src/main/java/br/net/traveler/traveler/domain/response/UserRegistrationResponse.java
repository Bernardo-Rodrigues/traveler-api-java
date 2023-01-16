package br.net.traveler.traveler.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {

    private URI uri;
}
