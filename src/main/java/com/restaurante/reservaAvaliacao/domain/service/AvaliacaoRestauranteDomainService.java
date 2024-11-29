package com.restaurante.reservaAvaliacao.domain.service;

import com.restaurante.reservaAvaliacao.domain.gateway.AvaliacaoRestauranteGateway;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvaliacaoRestauranteDomainService {

	@NonNull
	private final AvaliacaoRestauranteGateway gateway;
}
