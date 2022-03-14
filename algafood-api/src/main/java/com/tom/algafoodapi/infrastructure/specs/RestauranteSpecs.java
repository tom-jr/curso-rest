package com.tom.algafoodapi.infrastructure.specs;

import java.math.BigDecimal;

import com.tom.algafoodapi.domain.model.Restaurante;

import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecs {

    public static Specification<Restaurante> withFreeShip() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);

    }
}
