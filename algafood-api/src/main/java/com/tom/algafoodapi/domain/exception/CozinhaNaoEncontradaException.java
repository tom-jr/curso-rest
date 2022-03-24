package com.tom.algafoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntityNotFoundException {

    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }
    
}
