package com.restaurante.reservaAvaliacao.domain.gateway;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

public interface AvaliacaoRestauranteGateway {

	void createAvaliacaoRestaurante(AvaliacaoRestaurante avaliacao);

	Pagination<AvaliacaoRestaurante> findAll(int page, int size, Long seqResataurante);

}
