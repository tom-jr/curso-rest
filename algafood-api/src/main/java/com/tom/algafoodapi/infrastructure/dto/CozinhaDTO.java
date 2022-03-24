package com.tom.algafoodapi.infrastructure.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CozinhaDTO {
    
    @NotNull
    private Long id;

    @NotNull
    private String nome;
    
}
