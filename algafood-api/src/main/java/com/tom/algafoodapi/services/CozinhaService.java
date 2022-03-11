package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.repository.CozinhaRepository;
import com.tom.algafoodapi.infrastructure.dto.CozinhaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public CozinhaRepository getRepository() {
        return cozinhaRepository;
    }

    public Cozinha adicionar(CozinhaDTO dto) {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(dto.getNome());
        return getRepository().save(cozinha);
    }
}
