package ru.example.lections.sender.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.lections.sender.domain.Image;
import ru.example.lections.sender.domain.Message;
import ru.example.lections.sender.dto.message.MessageDto;
import ru.example.lections.sender.dto.message.SendMessageDto;

import java.util.List;

@Mapper
public interface MessageMapper {

    List<MessageDto> messagesToMessageDtos(List<Message> messages);

    MessageDto messageToMessageDto(Message message);

    @Mapping(target = "images", source = "images")
    Message sendMessageDtoToMessage(SendMessageDto message, List<Image> images);
}
