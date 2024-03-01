package ru.example.lections.sender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.lections.sender.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
