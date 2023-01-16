package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.exception.NotFoundException;
import br.net.traveler.traveler.domain.exception.UnauthorizedException;
import br.net.traveler.traveler.domain.mapper.UserMapper;
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
        verifySameUsernameOrEmail(dto, null);

        String encryptedPassword = cryptographyService.encrypt(dto.getPassword());
        dto.setPassword(encryptedPassword);

        User user = userRepository.save(userMapper.dtoToEntity(dto));

        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto findById(Integer id) {
        try {
            User user = userRepository.findById(id).get();
            return userMapper.entityToDto(user);
        } catch (Exception e) {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public UserDto updateUser(UserDto dto, Integer id) {
        User entity = userRepository.getReferenceById(id);

        if(entity == null){
            throw new NotFoundException("User not found");
        }

        verifySameUsernameOrEmail(dto, id);

        updateData(entity, dto);
        return userMapper.entityToDto(userRepository.save(entity));
    }

    @Override
    public UserDto identifyUser(UserDto dto) {
        User registeredUser = userRepository.findByEmail(dto.getEmail());

        if (registeredUser == null ||
            !cryptographyService.matches(dto.getPassword(), registeredUser.getPassword())
        ) {
            throw new UnauthorizedException("Credentials are incorrect");
        }

        return userMapper.entityToDto(registeredUser);
    }

    private void updateData(User entity, UserDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
    }

    private void verifySameUsernameOrEmail(UserDto dto, Integer idToUpdate){
        User userWithSameName = userRepository.findByUsername(dto.getUsername());

        if(userWithSameName != null && !userWithSameName.getId().equals(idToUpdate)) {
            throw new ConflictException("Name already registered");
        }

        User userWithSameEmail = userRepository.findByEmail(dto.getEmail());

        if(userWithSameEmail != null && !userWithSameEmail.getId().equals(idToUpdate)) {
            throw new ConflictException("Email already registered");
        }
    }
}
