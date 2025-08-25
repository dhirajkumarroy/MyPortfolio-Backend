package Dhiraj.sPortfolio.My_Portfolio.repository;

import Dhiraj.sPortfolio.My_Portfolio.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
