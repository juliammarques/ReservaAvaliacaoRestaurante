package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.exception.RestauranteNaoExiste;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteDomainService {

	@NonNull
	private final RestauranteGateway gateway;
	
	public void registraRestaurante (Restaurante restaurante) {
		gateway.createRestaurante(restaurante);
	}
	
	public void atualizaRestaurante (Restaurante restaurante) {
		gateway.getRestauranteById(restaurante.getSeqRestaurante())
				.orElseThrow(()->new RestauranteNaoExiste(restaurante.getSeqRestaurante()));
		gateway.updateRestaurante(restaurante);
	}
	
	public void deleteRestaurante(Long idRestaurante) {
		gateway.deleteRestaurante(idRestaurante);
	}
	
	public Pagination<Restaurante> buscaRestaurante(int page, int size){
		return gateway.findAll(page, size);
	}
}
