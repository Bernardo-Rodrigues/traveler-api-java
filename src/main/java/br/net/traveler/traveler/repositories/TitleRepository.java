package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Integer> {
}