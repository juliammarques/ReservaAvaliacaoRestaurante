package com.restaurante.reservaAvaliacao.domain.gateway;

import java.util.List;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;

public interface RestauranteGateway {

	void createRestaurante(Restaurante restaurante);

	void updateRestaurante(Restaurante restaurante);

	List<Restaurante> buscaListaRestaurante();

	void deleteRestaurante(Long idRestaurante);

}
