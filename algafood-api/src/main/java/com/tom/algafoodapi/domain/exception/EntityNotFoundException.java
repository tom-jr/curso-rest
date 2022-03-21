package com.tom.algafoodapi.domain.exception;

public abstract class EntityNotFoundException extends GeneralException{

    public EntityNotFoundException(String message) {
        super(message);
    }

}
