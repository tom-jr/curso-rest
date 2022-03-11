package com.tom.algafoodapi.services;

import java.util.Optional;

import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.domain.repository.CidadeRepository;
import com.tom.algafoodapi.infrastructure.dto.CidadeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired

    private EstadoService estadoService;

    public CidadeRepository getRepository() {
        return cidadeRepository;
    }

    public Cidade add(CidadeDAO dto) throws Exception {
        Optional<Estado> estado = this.estadoService.getRepository().findById(dto.getEstado().getId());

        if (estado.isEmpty()) {
            throw new Exception();
        }
        return this.cidadeRepository.save(new Cidade(null, dto.getNome(), estado.get()));
    }

}
