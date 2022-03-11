package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public EstadoRepository getRepository() {
        return estadoRepository;
    }

    
}
