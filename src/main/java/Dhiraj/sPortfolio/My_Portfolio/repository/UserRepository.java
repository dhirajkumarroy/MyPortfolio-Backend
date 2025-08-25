package Dhiraj.sPortfolio.My_Portfolio.repository;

import Dhiraj.sPortfolio.My_Portfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
