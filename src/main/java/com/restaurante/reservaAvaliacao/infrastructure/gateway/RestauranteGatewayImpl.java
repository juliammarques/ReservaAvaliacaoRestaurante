package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import java.time.LocalDate;
import java.util.List;

import com.restaurante.reservaAvaliacao.domain.entity.Restaurante;
import com.restaurante.reservaAvaliacao.domain.gateway.RestauranteGateway;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.entity.RestauranteEntity;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IRestauranteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteGatewayImpl implements RestauranteGateway {

	private final IRestauranteRepository repository;
	
	@Override
	public void createRestaurante(Restaurante restaurante) {
		restaurante.setDataCadastro(LocalDate.now());
		repository.save(RestauranteEntity.of(restaurante));
	}
	
	@Override
	public List<Restaurante> buscaListaRestaurante(){
		return null;
	}
	
	@Override
	public void updateRestaurante(Restaurante restaurante) {
		
		repository.save(RestauranteEntity.of(restaurante));
	}
	
	@Override
	public void deleteRestaurante (Long idRestaurante) {
		repository.deleteById(idRestaurante);
	}
}
