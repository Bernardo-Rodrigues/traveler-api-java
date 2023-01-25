package br.net.traveler.traveler.unit.services;

import br.net.traveler.traveler.domain.dto.UserDto;
import br.net.traveler.traveler.domain.entities.Destination;
import br.net.traveler.traveler.domain.exception.ConflictException;
import br.net.traveler.traveler.domain.mapper.DestinationMapper;
import br.net.traveler.traveler.domain.mapper.UserMapper;
import br.net.traveler.traveler.domain.mother.UserMother;
import br.net.traveler.traveler.repositories.DestinationRepository;
import br.net.traveler.traveler.repositories.UserRepository;
import br.net.traveler.traveler.services.CryptographyService;
import br.net.traveler.traveler.services.impl.DestinationServiceImpl;
import br.net.traveler.traveler.services.impl.UserServiceImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class DestinationServiceUnitTest implements WithAssertions {

    @InjectMocks
    private DestinationServiceImpl destinationService;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private DestinationMapper destinationMapper;

    @Test
    void givenAListDestinationsAttemptWhenThereIsNoNameParameterThenCallListAll(){
        given(destinationRepository.findAll()).willReturn(new ArrayList<>());

        destinationService.list("");

        verify(destinationRepository).findAll();
    }

    @Test
    void givenAListDestinationsAttemptWhenThereIsNoNameParameterThenCallListAllByName(){
        given(destinationRepository.findAllByNameStartsWith("name")).willReturn(new ArrayList<>());

        destinationService.list("name");

        verify(destinationRepository).findAllByNameStartsWith("name");
    }
}
