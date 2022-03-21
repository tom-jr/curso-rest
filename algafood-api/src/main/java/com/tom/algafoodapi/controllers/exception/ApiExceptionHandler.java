package com.tom.algafoodapi.controllers.exception;

import java.time.LocalDateTime;

import com.tom.algafoodapi.domain.exception.EntityInUseException;
import com.tom.algafoodapi.domain.exception.EntityNotFoundException;
import com.tom.algafoodapi.domain.exception.GeneralException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandlerMethod(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> entityLinkedHandlerMethod(EntityInUseException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(StandardError.builder()
                        .dateTime(LocalDateTime.now())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalExceptionHandlerMethod(GeneralException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                StandardError.builder()
                        .dateTime(LocalDateTime.now())
                        .message(e.getMessage())
                        .build());

    }

}
