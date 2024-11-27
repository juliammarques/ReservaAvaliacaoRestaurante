package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.AvaliacaoRestauranteEntity;

public interface IAvaliacaoRestauranteRepository extends JpaRepository<AvaliacaoRestauranteEntity, Long> {

}
