package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.QuantitativoMesaEntity;

public interface IQuantitativoMesaRepository extends JpaRepository<QuantitativoMesaEntity, Long> {

}
