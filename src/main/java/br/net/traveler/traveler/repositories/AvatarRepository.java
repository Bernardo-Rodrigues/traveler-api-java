package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
}