package ru.example.lections.sender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BaseNotFoundException extends ResponseStatusException {

    private final String messageCode;
    private final Object[] args;

    public BaseNotFoundException(String messageCode, Object... args) {
        super(HttpStatus.NOT_FOUND);
        this.messageCode = messageCode;
        this.args = args;
    }

    public String getMessageCode() {
        return this.messageCode;
    }

    public Object[] getArgs() {
        return this.args;
    }
}
