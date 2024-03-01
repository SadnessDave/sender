package ru.example.lections.sender.exception;

public class MessageNotFoundException extends BaseNotFoundException {

    public MessageNotFoundException(Object... args) {
        super("exception.message.not_found", args);
    }
}
