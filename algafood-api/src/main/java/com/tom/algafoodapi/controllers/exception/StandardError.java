package com.tom.algafoodapi.controllers.exception;

import java.util.List;

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

    private List<Field> fields;

    @Getter
    @Builder
    public static class Field{
        private String name;
		private String userMessage;
    }
}