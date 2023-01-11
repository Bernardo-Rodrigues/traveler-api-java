package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        User userWithSameName = userRepository.findByUsername(user.getUsername());
        User userWithSameEmail = userRepository.findByEmail(user.getEmail());

        if(userWithSameName != null || userWithSameEmail != null) {
            throw new ConflictException("User already registered", "401");
        }

        userRepository.save(user);
    }
}
