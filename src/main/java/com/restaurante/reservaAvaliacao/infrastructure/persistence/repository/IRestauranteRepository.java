package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;

public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long>{

}
