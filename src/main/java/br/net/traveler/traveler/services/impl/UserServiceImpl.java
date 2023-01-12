package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import br.net.traveler.traveler.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.CryptographyService;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CryptographyService cryptographyService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        User userWithSameName = userRepository.findByUsername(user.getUsername());

        if(userWithSameName != null) {
            throw new ConflictException("User already registered", "409");
        }

        User userWithSameEmail = userRepository.findByEmail(user.getEmail());

        if(userWithSameEmail != null) {
            throw new ConflictException("User already registered", "409");
        }

        String encryptedPassword = cryptographyService.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
    }

    @Override
    public void identifyUser(User user) {
        User registeredUser = userRepository.findByEmail(user.getEmail());

        if (registeredUser == null ||
            !cryptographyService.matches(user.getPassword(), registeredUser.getPassword())
        ) {
            throw new UnauthorizedException("Credentials are incorrect", "401");
        }
    }
}
