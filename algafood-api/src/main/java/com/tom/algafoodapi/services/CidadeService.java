package com.tom.algafoodapi.services;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.tom.algafoodapi.domain.exception.EntityInUseException;
import com.tom.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.tom.algafoodapi.domain.exception.GeneralException;
import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.domain.repository.CidadeRepository;
import com.tom.algafoodapi.infrastructure.dto.CidadeDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Cidade add(Cidade cidade, CidadeDTO dto) throws Exception {
        try {
            Estado estado = this.estadoService.findById(dto.getEstado().getId());
            cidade = Cidade.builder().nome(dto.getNome())
                    .id(cidade.getId() != null ? cidade.getId() : null)
                    .estado(estado).build();

            return this.getRepository().save(cidade);
        } catch (EstadoNaoEncontradaException e) {
            throw new GeneralException(
                    StringUtils.entityNotExist(dto.getEstado().getId(), Estado.class.getSimpleName()));
        }

    }

    public Cidade findById(Long cidadeId) {
        return this.getRepository().findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(
                StringUtils.entityNotExist(cidadeId, Cidade.class.getSimpleName())));
    }

    public void delete(Long cidadeId) {
        Cidade cidade = this.findById(cidadeId);

        try {
            this.getRepository().delete(cidade);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(StringUtils.entityLinked(Cidade.class.getSimpleName()));
        }
    }

}
