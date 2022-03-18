package com.tom.algafoodapi.services;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.tom.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.repository.CozinhaRepository;
import com.tom.algafoodapi.infrastructure.dto.CozinhaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public void delete(Long cozinhaId) {
        try {
            this.cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            throw new EntidadeNaoEncontradaException(
                    StringUtils.entityNotExist(cozinhaId, Cozinha.class.getSimpleName()));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(StringUtils.entityLinked(Cozinha.class.getSimpleName()));
        }
    }
}
