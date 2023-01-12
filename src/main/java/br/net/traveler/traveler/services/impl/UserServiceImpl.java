package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.response.UserAuthenticationResponse;
import br.net.traveler.traveler.domain.response.UserRegistrationResponse;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.CryptographyService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CryptographyService cryptographyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto dto) {
        User userWithSameName = userRepository.findByUsername(dto.getUsername());

        if(userWithSameName != null) {
            throw new ConflictException("User already registered", "409");
        }

        User userWithSameEmail = userRepository.findByEmail(dto.getEmail());

        if(userWithSameEmail != null) {
            throw new ConflictException("User already registered", "409");
        }

        String encryptedPassword = cryptographyService.encrypt(dto.getPassword());
        dto.setPassword(encryptedPassword);

        User user = userRepository.save(userMapper.dtoToEntity(dto));

        System.out.println(user);
        return userMapper.entityToDto(user);
    }

    @Override
    public String identifyUser(UserDto user) {
        User registeredUser = userRepository.findByEmail(user.getEmail());

        if (registeredUser == null ||
            !cryptographyService.matches(user.getPassword(), registeredUser.getPassword())
        ) {
            throw new UnauthorizedException("Credentials are incorrect", "401");
        }

        return "";
    }
}
