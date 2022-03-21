package com.tom.algafoodapi.controllers.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardError {
    
    private LocalDateTime dateTime;

    private String message;
}