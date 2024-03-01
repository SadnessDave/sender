package ru.example.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.authserver.domain.User;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
