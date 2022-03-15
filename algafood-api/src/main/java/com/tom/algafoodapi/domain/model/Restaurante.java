package com.tom.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Restaurante {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;


    // constructor
    
        public Restaurante(Long id, String nome, BigDecimal taxaFrete, LocalDateTime dataCriacao,
                LocalDateTime dataAtualizacao, Endereco endereco, Cozinha cozinha) {
            this.id = id;
            this.nome = nome;
            this.taxaFrete = taxaFrete;
            this.dataCriacao = dataCriacao;
            this.dataAtualizacao = dataAtualizacao;
            this.endereco = endereco;
            this.cozinha = cozinha;
        }

    //embedded


    @JsonIgnore
    @Embedded
    private Endereco endereco;

    // relations
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
