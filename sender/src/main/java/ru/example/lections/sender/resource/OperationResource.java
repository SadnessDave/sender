package ru.example.lections.sender.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.lections.sender.domain.Operation.OperationType;
import ru.example.lections.sender.dto.OperationDto;
import ru.example.lections.sender.service.OperationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
@RequiredArgsConstructor
public class OperationResource {

    private final OperationService service;

    @GetMapping("/{type}")
    public List<OperationDto> getOperations(@PathVariable OperationType type) {
        return service.getOperationsByType(type);
    }
}
