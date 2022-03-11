package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.repository.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;



    public CidadeRepository getRepository() {
        return cidadeRepository;
    }    
}
