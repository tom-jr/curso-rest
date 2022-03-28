package com.tom.algafoodapi.infrastructure.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.tom.algafoodapi.controllers.validation_groups.Groups;

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

    @NotBlank
    @NotNull
    private String nome;

    @DecimalMin(value = "0")
    private BigDecimal taxaFrete;

    //relations
    @NotNull
    @Valid
    @ConvertGroup(from =Default.class ,to = Groups.CozinhaId.class)
    private CozinhaDTO cozinha;
}
