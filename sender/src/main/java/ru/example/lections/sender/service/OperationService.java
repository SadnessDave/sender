package ru.example.lections.sender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.lections.sender.domain.Operation.OperationType;
import ru.example.lections.sender.dto.OperationDto;
import ru.example.lections.sender.mapper.OperationMapper;
import ru.example.lections.sender.repository.OperationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository repository;
    private final OperationMapper mapper;

    public void logOperation(OperationDto operationDto) {
        repository.save(mapper.operationDtoToOperation(operationDto));
    }

    public List<OperationDto> getOperationsByType(OperationType type) {
        return mapper.operationsToOperationDtos(repository.findAllByType(type));
    }
}
