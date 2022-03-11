package com.tom.algafoodapi.infrastructure.repository.custom;

import java.util.Optional;

import javax.persistence.EntityManager;

import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        // TODO Auto-generated constructor stub
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        // TODO Auto-generated method stub
        String jpql = String.format("FROM %s", this.getDomainClass().getName());
        T t = entityManager.createQuery(jpql, this.getDomainClass())
                .setMaxResults(1)
                .getSingleResult();
        return Optional.ofNullable(t);
    }

}
