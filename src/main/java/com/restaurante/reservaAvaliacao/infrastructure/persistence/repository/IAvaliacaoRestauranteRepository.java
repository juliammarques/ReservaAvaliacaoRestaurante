package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.AvaliacaoRestauranteEntity;

public interface IAvaliacaoRestauranteRepository extends JpaRepository<AvaliacaoRestauranteEntity, Long>,JpaSpecificationExecutor<AvaliacaoRestauranteEntity> {

}
