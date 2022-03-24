package com.tom.algafoodapi.infrastructure.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestauranteDTO {
    
    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    //relations
    @NotNull
    @Valid
    private CozinhaDTO cozinha;
}
