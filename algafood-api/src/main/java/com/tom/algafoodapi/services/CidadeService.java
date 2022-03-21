package com.tom.algafoodapi.services;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.tom.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.tom.algafoodapi.domain.exception.GeneralException;
import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.domain.repository.CidadeRepository;
import com.tom.algafoodapi.infrastructure.dto.CidadeDAO;

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

    public Cidade add(Cidade cidade, CidadeDAO dto) throws Exception {
        try {
            Estado estado = this.estadoService.findById(dto.getEstado().getId());
            cidade = Cidade.builder().nome(dto.getNome())
                    .id(cidade.getId() != null ? cidade.getId() : null)
                    .estado(estado).build();

            return this.getRepository().save(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            // TODO: handle exception
            throw new GeneralException(e.getMessage());
        }

    }

    public Cidade findById(Long cidadeId) {
        return this.getRepository().findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                StringUtils.entityNotExist(cidadeId, Cidade.class.getSimpleName())));
    }

    public void delete(Long cidadeId) {
        Cidade cidade = this.findById(cidadeId);

        try {
            this.getRepository().delete(cidade);
        } catch (DataIntegrityViolationException e) {
            // TODO: handle exception
            throw new EntidadeEmUsoException(StringUtils.entityLinked(Cidade.class.getSimpleName()));
        }
    }

}
