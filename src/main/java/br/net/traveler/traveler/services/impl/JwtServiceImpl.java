package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.JwtService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    Environment environment;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public String generateToken(UserDto userDto) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDto.getId());
    }

    @Override
    public UserDto validateToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(environment.getProperty("jwtsecret"));

        try {
            Claims claims = parser.parseClaimsJws(token).getBody();
            UserDto dto = userMapper.entityToDto(userRepository.findById(claims.getSubject()).get());
            if(dto == null) throw new UnauthorizedException("JWT invalid user");
            return dto;
        } catch (SignatureException e) {
            throw new UnauthorizedException("JWT invalid signature");
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("JWT expired");
        }
    }

    private String createToken(Map<String, Object> claims, String subject) {
        String SECRET_KEY = environment.getProperty("jwtsecret");
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

}
