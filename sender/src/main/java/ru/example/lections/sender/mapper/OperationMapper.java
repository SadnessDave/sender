package ru.example.lections.sender.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.lections.sender.domain.Operation;
import ru.example.lections.sender.dto.OperationDto;

import java.util.List;

@Mapper
public interface OperationMapper {

    List<OperationDto> operationsToOperationDtos(List<Operation> operations);

    @Mapping(target = "id", expression = "java(null)")
    Operation operationDtoToOperation(OperationDto operationDto);
}
