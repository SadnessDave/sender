package ru.example.lections.sender.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.example.lections.sender.exception.BaseNotFoundException;
import ru.example.lections.sender.service.MessageRenderer;

@RestControllerAdvice
@RequiredArgsConstructor
public class BaseNotFoundExceptionHandler {

    private final MessageRenderer messageRenderer;

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(BaseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(messageRenderer.render(ex.getMessageCode(), ex.getArgs())));
    }

    @Getter
    @RequiredArgsConstructor
    public static class ErrorResponse {

        private final String message;
    }
}
