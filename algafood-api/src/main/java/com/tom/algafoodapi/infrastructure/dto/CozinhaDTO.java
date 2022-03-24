package com.tom.algafoodapi.infrastructure.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.tom.algafoodapi.controllers.validation_groups.Groups;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CozinhaDTO {
    
    @NotNull(groups = Groups.CozinhaId.class)
    private Long id;

    @NotNull
    @NotBlank
    private String nome;
    
}
