package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.domain.repository.EstadoRepository;
import com.tom.algafoodapi.infrastructure.dto.EstadoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public EstadoRepository getRepository() {
        return estadoRepository;
    }

    public Estado add(EstadoDTO dto) {
        Estado estado = new Estado(null, dto.getNome());
        return estado;
    }

    
}
