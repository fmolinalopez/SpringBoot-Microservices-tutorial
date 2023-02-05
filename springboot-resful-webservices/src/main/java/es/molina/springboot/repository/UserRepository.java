package es.molina.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.molina.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
}
