package com.restaurante.reservaAvaliacao.infrastructure.gateway;

import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;
import com.restaurante.reservaAvaliacao.infrastructure.persistence.repository.IAvaliacaoRestauranteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvaliacaoRestauranteGatewayImpl implements AvaliacaoRestauranteGateway{

	private final IAvaliacaoRestauranteRepository repository;

}
