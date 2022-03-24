package com.tom.algafoodapi.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDAO {

    private Long id;

    private String nome;

    private EstadoDTO estado;
    
}
