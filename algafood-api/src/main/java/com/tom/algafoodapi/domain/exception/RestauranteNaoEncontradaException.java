package com.tom.algafoodapi.domain.exception;

public class RestauranteNaoEncontradaException extends EntityNotFoundException {

    public RestauranteNaoEncontradaException(String message) {
        super(message);
    }

}
