package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        User userWithSameName = userRepository.findByUsername(user.getUsername());

        if(userWithSameName != null) throw new ConflictException("Username already registered", "401");
    }
}