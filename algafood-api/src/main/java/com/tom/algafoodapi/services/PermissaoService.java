package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.repository.PermissaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {
    
    @Autowired
    private PermissaoRepository permissaoRepository;

    public PermissaoRepository getRepository() {
        return permissaoRepository;
    }

    
}
