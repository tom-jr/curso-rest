package com.tom.algafoodapi.domain.exception;

public class EstadoNaoEncontradaException extends EntityNotFoundException {

    public EstadoNaoEncontradaException(String message) {
        super(message);
    }
    
}
