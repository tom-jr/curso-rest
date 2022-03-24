package com.tom.algafoodapi.controllers.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardError {
    
    private Integer status;

    private String type;

    private String title;

    private String detail;

    private String userMenssage;
}