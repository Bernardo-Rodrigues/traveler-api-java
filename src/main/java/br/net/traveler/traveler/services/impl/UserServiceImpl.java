package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.CryptographyException;
import br.net.traveler.traveler.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    CryptographyServiceImpl cryptographyService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user) {
        User userWithSameName = userRepository.findByUsername(user.getUsername());
        User userWithSameEmail = userRepository.findByEmail(user.getEmail());

        if(userWithSameName != null || userWithSameEmail != null) {
            throw new ConflictException("User already registered", "401");
        }

        String encryptedPassword = cryptographyService.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
    }


}
