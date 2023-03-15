package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.entities.User;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(String id) {
        User user = User.builder()
                .id(id)
                .build();

        user = userRepository.save(user);

        return userMapper.entityToDto(user);
    }

    @Override
    public void deleteUser(String id) {
        User entity = userRepository.getReferenceById(id);

        if(entity == null){
            throw new NotFoundException("User not found");
        }

        userRepository.delete(entity);
    }
}
