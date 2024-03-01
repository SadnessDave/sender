package ru.example.lections.sender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.example.lections.sender.domain.Message;
import ru.example.lections.sender.domain.Operation.OperationType;
import ru.example.lections.sender.dto.ImageDto;
import ru.example.lections.sender.dto.OperationDto;
import ru.example.lections.sender.dto.message.MessageDto;
import ru.example.lections.sender.dto.message.SendMessageDto;
import ru.example.lections.sender.exception.ImagesNotFoundException;
import ru.example.lections.sender.exception.MessageNotFoundException;
import ru.example.lections.sender.mapper.ImagesMapper;
import ru.example.lections.sender.mapper.MessageMapper;
import ru.example.lections.sender.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    private final ImagesMapper imageMapper;
    private final MessageMapper mapper;

    private final OperationService operationService;
    private final ImageService imageService;

    public List<MessageDto> getAllMessages() {
        return mapper.messagesToMessageDtos(repository.findAll());
    }

    @Cacheable(value = "MessageService::getMessageById", key = "#id")
    public MessageDto getMessageById(int id) {
        Optional<Message> messageOptional = repository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new MessageNotFoundException(id);
        }

        MessageDto message = mapper.messageToMessageDto(messageOptional.get());
        operationService.logOperation(
                new OperationDto(
                        String.format("Read message: %s", message),
                        LocalDateTime.now(),
                        OperationType.READ
                )
        );

        return message;
    }

    @Cacheable(value = "MessageService::getMessageImages", key = "#id + '.images'")
    public List<ImageDto> getMessageImages(int id) {
        Optional<Message> messageOptional = repository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new MessageNotFoundException(id);
        }

        List<ImageDto> images = imageMapper.imagesToImagesDto(messageOptional.get().getImages());
        operationService.logOperation(
                new OperationDto(
                        String.format("Read message images: %s", images),
                        LocalDateTime.now(),
                        OperationType.READ
                )
        );

        return images;
    }

    public SendMessageDto addMessage(SendMessageDto messageDto) {
        List<Integer> imagesId = messageDto.getImagesId() != null
                ? messageDto.getImagesId()
                : List.of();

        if (!imagesId.isEmpty() && !imageService.existsAll(imagesId)) {
            throw new ImagesNotFoundException();
        }

        repository.save(mapper.sendMessageDtoToMessage(
                messageDto,
                imageService.getAllImages(imagesId)
        ));

        operationService.logOperation(
                new OperationDto(
                        String.format("Send message: %s", messageDto),
                        LocalDateTime.now(),
                        OperationType.WRITE
                )
        );

        return messageDto;
    }
}
