package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
    public User findByEmail(String email);
}
