package com.tom.algafoodapi.infrastructure.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tom.algafoodapi.controllers.validation_groups.Groups;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstadoDTO {

    @NotNull(groups = Groups.EstadoId.class)
    private Long id;

    @NotNull
    @NotBlank
    private String nome;
}
