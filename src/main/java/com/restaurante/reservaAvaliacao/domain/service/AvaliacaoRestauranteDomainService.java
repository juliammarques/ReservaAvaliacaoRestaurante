package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.AvaliacaoRestaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvaliacaoRestauranteDomainService {

	@NonNull
	private final AvaliacaoRestauranteGateway gateway;
	
	public void adicionaAvaliacaoRestaurante (AvaliacaoRestaurante avaliacao) {
		gateway.createAvaliacaoRestaurante(avaliacao); 
	}
	
	public Pagination<AvaliacaoRestaurante> buscaAvaliacaoPorRestaurante(int page, int size,Long seqRestaurante){
		return gateway.findAll(page, size, seqRestaurante);
	}
}
