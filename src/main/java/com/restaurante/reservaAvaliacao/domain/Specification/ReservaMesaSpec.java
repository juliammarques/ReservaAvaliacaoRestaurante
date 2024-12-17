package com.restaurante.reservaAvaliacao.domain.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity.StatusReserva;

public class ReservaMesaSpec {

	public static Specification<ReservaMesaEntity> buscaPorDocumentoClienteESeqRestaurante(String documento,Long seqRestaurante){
		return (root,query,cb) -> cb.and(cb.equal(root.get("documentoCliente"), documento),
				cb.equal(root.get("seqRestaurante"), seqRestaurante),
				cb.equal(root.get("status"), StatusReserva.ENCERRADA));
	}
	
	public static Specification<ReservaMesaEntity> buscaPorSeqRestaurante(Long seqRestaurante){
		return (root,query,cb) -> cb.and(cb.equal(root.get("seqRestaurante"), seqRestaurante));
	}
}
