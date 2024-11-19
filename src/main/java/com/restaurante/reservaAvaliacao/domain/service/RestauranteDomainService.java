package com.restaurante.reservaAvaliacao.domain.service;

import java.util.List;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;

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
		gateway.updateRestaurante(restaurante);
	}
	
	public void deleteRestaurante(Long idRestaurante) {
		gateway.deleteRestaurante(idRestaurante);
	}
	
	public List<Restaurante> buscaRestaurante(){
		return gateway.buscaListaRestaurante();	
	}
}
