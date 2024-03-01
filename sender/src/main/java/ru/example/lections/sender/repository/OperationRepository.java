package ru.example.lections.sender.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.example.lections.sender.domain.Operation;
import ru.example.lections.sender.domain.Operation.OperationType;

import java.util.List;

public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findAllByType(OperationType type);
}
