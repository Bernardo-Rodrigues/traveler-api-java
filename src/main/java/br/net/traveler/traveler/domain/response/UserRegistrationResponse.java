package br.net.traveler.traveler.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer avatarId;
    private Integer titleId;
}
