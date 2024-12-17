package com.restaurante.reservaAvaliacao.domain.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.AvaliacaoRestauranteEntity;

public class AvaliacaoRestauranteSpec {

	public static Specification<AvaliacaoRestauranteEntity> buscaAvaliacaoPorSeqRestaurante(Long seqRestaurante){
		return (root,query,cb) -> cb.and(cb.equal(root.get("seqRestaurante"), seqRestaurante));
	}
}
