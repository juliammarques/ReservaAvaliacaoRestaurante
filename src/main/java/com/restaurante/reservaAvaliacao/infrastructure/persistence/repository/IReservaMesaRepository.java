package com.restaurante.reservaAvaliacao.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;

public interface IReservaMesaRepository extends JpaRepository<ReservaMesaEntity, Long>,JpaSpecificationExecutor<ReservaMesaEntity> {

}
