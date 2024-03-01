package ru.example.lections.sender.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.lections.sender.dto.ImageDto;
import ru.example.lections.sender.dto.message.MessageDto;
import ru.example.lections.sender.dto.message.SendMessageDto;
import ru.example.lections.sender.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageResource {

    private final MessageService messageService;

    @GetMapping("/messages")
    public List<MessageDto> getMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/message/{id}")
    public MessageDto getMessage(@PathVariable int id) {
        return messageService.getMessageById(id);
    }

    @GetMapping("/message/{id}/images")
    public List<ImageDto> getMessageImages(@PathVariable int id) {
        return messageService.getMessageImages(id);
    }

    @PostMapping("message/send")
    public SendMessageDto sendMessage(@RequestBody SendMessageDto messageDto) {

        return messageService.addMessage(messageDto);
    }
}
