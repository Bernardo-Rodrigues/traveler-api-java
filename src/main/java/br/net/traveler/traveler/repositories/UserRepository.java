package br.net.traveler.traveler.repositories;

import br.net.traveler.traveler.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
    public User findByEmail(String email);
}
