package br.net.traveler.traveler.services.impl;

import br.net.traveler.traveler.domain.dto.ContinentDto;
import br.net.traveler.traveler.domain.mapper.ContinentMapper;
import br.net.traveler.traveler.repositories.ContinentRepository;
import br.net.traveler.traveler.services.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    ContinentMapper continentMapper;


    @Override
    public List<ContinentDto> list() {
        return continentMapper.entityListToDtoList(continentRepository.findAll());
    }
}
