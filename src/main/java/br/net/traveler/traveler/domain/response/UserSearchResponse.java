package br.net.traveler.traveler.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResponse {

    Integer id;
    String username;
    String email;
    String password;
    Integer avatarId;
    Integer titleId;
}
