package com.tom.algafoodapi.services;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.exception.EntityInUseException;
import com.tom.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.domain.repository.EstadoRepository;
import com.tom.algafoodapi.infrastructure.dto.EstadoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public EstadoRepository getRepository() {
        return estadoRepository;
    }

    public Estado add(Estado estado, EstadoDTO dto) {
        return Estado.builder()
                .nome(dto.getNome())
                .id(estado.getId() != null ? estado.getId() : dto.getId()).build();

    }

    public Estado findById(Long estadoId) {
        return this.getRepository().findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradaException(
                StringUtils.entityNotExist(estadoId, Estado.class.getSimpleName())));
    }

    public void delete(Long estadoId) {
        Estado estado = this.findById(estadoId);
        try {
            this.getRepository().delete(estado);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(StringUtils.entityLinked(Estado.class.getSimpleName()));
        }
    }

}
