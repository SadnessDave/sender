package ru.example.lections.sender.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageDto implements Serializable {

    private String author;
    private String content;
    private LocalDateTime lastModifiedDate;
}
