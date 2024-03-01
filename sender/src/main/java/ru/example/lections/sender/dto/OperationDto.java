package ru.example.lections.sender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.example.lections.sender.domain.Operation.OperationType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OperationDto {

    private String content;
    private LocalDateTime date;
    private OperationType type;
}
