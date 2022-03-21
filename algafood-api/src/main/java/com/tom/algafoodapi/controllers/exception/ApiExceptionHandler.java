package com.tom.algafoodapi.controllers.exception;

import java.time.LocalDateTime;

import com.tom.algafoodapi.domain.exception.EntityInUseException;
import com.tom.algafoodapi.domain.exception.EntityNotFoundException;
import com.tom.algafoodapi.domain.exception.GeneralException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandlerMethod(EntityNotFoundException e, WebRequest request) {
        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> entityLinkedHandlerMethod(EntityInUseException e, WebRequest request) {

        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalExceptionHandlerMethod(GeneralException e, WebRequest request) {
        return this.handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        if (body == null) {
            body = StandardError.builder()
                    .dateTime(LocalDateTime.now())
                    .message(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = StandardError.builder()
                    .dateTime(LocalDateTime.now())
                    .message((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
