package com.tom.algafoodapi.infrastructure.dto;

import java.math.BigDecimal;

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
    private CozinhaDTO cozinha;
}
