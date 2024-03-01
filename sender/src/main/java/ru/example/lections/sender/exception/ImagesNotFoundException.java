package ru.example.lections.sender.exception;

public class ImagesNotFoundException extends BaseNotFoundException {

    public ImagesNotFoundException() {
        super("exception.images.not_found");

    }
}
