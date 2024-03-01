package ru.example.lections.sender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ImageDto implements Serializable {

    private String name;
    private long size;
    private String link;
}
