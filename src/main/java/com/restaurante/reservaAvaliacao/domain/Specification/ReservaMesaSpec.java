package com.restaurante.reservaAvaliacao.domain.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.ReservaMesaEntity;

public class ReservaMesaSpec {

	public static Specification<ReservaMesaEntity> buscaPorDocumentoCliente(String documento){
		return (root,query,cb) -> cb.and(cb.equal(root.get("documentoCliente"), documento));
	}
	
	public static Specification<ReservaMesaEntity> buscaPorSeqRestaurante(Long seqRestaurante){
		return (root,query,cb) -> cb.and(cb.equal(root.get("seqRestaurante"), seqRestaurante));
	}
}
