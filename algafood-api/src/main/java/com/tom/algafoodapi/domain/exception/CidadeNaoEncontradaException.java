package com.tom.algafoodapi.domain.exception;

public class CidadeNaoEncontradaException extends EntityNotFoundException{

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }    
}
