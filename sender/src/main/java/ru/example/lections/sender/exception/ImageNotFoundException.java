package ru.example.lections.sender.exception;

public class ImageNotFoundException extends BaseNotFoundException {

    public ImageNotFoundException(Object... args) {
        super("exception.image.not_found", args);
    }
}
