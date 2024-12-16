package com.restaurante.reservaAvaliacao.domain.gateway;

import java.util.Optional;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.pagination.Pagination;

public interface RestauranteGateway {

	void createRestaurante(Restaurante restaurante);

	void updateRestaurante(Restaurante restaurante);

    Pagination<Restaurante> findAll(int page, int size);

	void deleteRestaurante(Long idRestaurante);

	Optional<Restaurante> getRestauranteById(Long parquimetroId);

}
