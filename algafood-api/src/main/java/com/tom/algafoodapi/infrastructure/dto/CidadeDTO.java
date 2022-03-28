package com.tom.algafoodapi.infrastructure.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.tom.algafoodapi.controllers.validation_groups.Groups;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {

    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @Valid
    @ConvertGroup(from =Default.class ,to =Groups.EstadoId.class)
    private EstadoDTO estado;
    
}
