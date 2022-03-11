package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.repository.FormaPagamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {
    
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoRepository getRepository() {
        return formaPagamentoRepository;
    }

}
