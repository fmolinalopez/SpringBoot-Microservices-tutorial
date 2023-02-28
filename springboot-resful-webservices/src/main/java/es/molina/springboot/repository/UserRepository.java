package es.molina.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.molina.springboot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
}
